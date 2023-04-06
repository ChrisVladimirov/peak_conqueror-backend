package com.example.backend.web;

import com.example.backend.models.DTOs.LoggedUserView;
import com.example.backend.models.DTOs.UserDetailsView;
import com.example.backend.models.DTOs.UserLoginDTO;
import com.example.backend.models.DTOs.UserRegisterDTO;
import com.example.backend.services.UserService;
import com.example.backend.util.exceptions.UserAlreadyRegisteredException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthenticationProvider authenticationProvider;
    private final JwtEncoder jwtEncoder;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService,
                          AuthenticationProvider authenticationProvider, JwtEncoder jwtEncoder, ModelMapper mapper) {
        this.userService = userService;
        this.authenticationProvider = authenticationProvider;
        this.jwtEncoder = jwtEncoder;
        this.mapper = mapper;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> registerUser
            (@RequestBody @Valid UserRegisterDTO registerDTO) {
        boolean successfulRegistration = this.userService.register(registerDTO);

        if (!successfulRegistration) throw new UserAlreadyRegisteredException();

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid UserLoginDTO loginDTO) {
        boolean successfulLogin = this.userService.login(loginDTO);

        if (!successfulLogin) return new ResponseEntity<>
                ("Invalid username or password!", HttpStatus.UNAUTHORIZED);

        UserDetailsView user = this.userService.getParticularUser(loginDTO.getUsername());
        Authentication authentication = this.authenticationProvider.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), loginDTO.getPassword())
        );
        if (authentication.isAuthenticated()) {
            Instant now = Instant.now();
            long expiry = 86400L;

            var scopes = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("self/principal")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(user.getUsername())
                    .claim("scope", scopes)
                    .build();

            var jwtToken = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            LoggedUserView principal = this.mapper.map(user, LoggedUserView.class);
            principal.setToken(jwtToken);

            return ResponseEntity.ok(principal);//fixme: not to be implemented this way in production!
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("");
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDetailsView>> getAllUsers() {
        List<UserDetailsView> allUsers = this.userService.getAllUsers();
        return ResponseEntity.ok(allUsers);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDetailsView> getParticularUser(@PathVariable String username) {
        UserDetailsView particularUser = this.userService.getParticularUser(username);
        return ResponseEntity.ok(particularUser);
    }

    @PatchMapping("/me/edit-thoughts")
    public ResponseEntity<?> editThoughts(@RequestParam(name = "id") long userId,
                                          @RequestParam(name = "editedThoughts") String newThoughts) {
        boolean successfulEdit = this.userService.editThoughts(userId, newThoughts);
        if (!successfulEdit) return new ResponseEntity<>("This is not a valid update!", HttpStatus.BAD_REQUEST);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    public ResponseEntity<?> alreadyRegisteredHandler(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }
}
