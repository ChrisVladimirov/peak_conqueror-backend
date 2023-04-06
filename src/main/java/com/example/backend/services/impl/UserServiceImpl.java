package com.example.backend.services.impl;

import com.example.backend.models.DTOs.UserDetailsView;
import com.example.backend.models.DTOs.UserLoginDTO;
import com.example.backend.models.DTOs.UserRegisterDTO;
import com.example.backend.models.entities.UserEntity;
import com.example.backend.models.entities.UserRoleEntity;
import com.example.backend.models.enums.UserRoleEnum;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.UserRoleService;
import com.example.backend.services.UserService;
import com.example.backend.util.exceptions.InvalidUserIdException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private ModelMapper mapper;
    private final UserRoleService userRoleService;
    private final UserDetailsService appUserDetailsService;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           ModelMapper mapper, UserRoleService userRoleService,
                           UserDetailsService appUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.userRoleService = userRoleService;
        this.appUserDetailsService = appUserDetailsService;
    }

    @Override
    public boolean register(UserRegisterDTO registerDTO) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(registerDTO.getUsername());

        if (byUsername.isPresent()) {
            return false;
        }

        UserEntity userToRegister = this.mapper.map(registerDTO, UserEntity.class);
        String encodedPassword = this.passwordEncoder.encode(userToRegister.getPassword());

        UserRoleEntity userRole = this.userRoleService.getRoleByRoleEnum(UserRoleEnum.MEMBER);
        userToRegister.addRole(userRole);

        userToRegister.setPassword(encodedPassword);

        this.userRepository.save(userToRegister);

        //assignSecurityToken(userToRegister);
        return true;
    }

    private void assignSecurityToken(UserEntity userToRegister) {
        UserDetails userDetails = this.appUserDetailsService.loadUserByUsername(userToRegister.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public List<UserDetailsView> getAllUsers() {
        return this.userRepository.findAll().stream().map(mapEntityToDetailsView).toList();
    }

    @Override
    public UserDetailsView getParticularUser(String username) {
        return this.userRepository
                .findByUsername(username)
                .map(mapEntityToDetailsView)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Sorry! No username with this username."));
    }

    @Override
    public UserDetailsView getParticularUser(long id) {
        return this.userRepository.findById(id).map(mapEntityToDetailsView).orElseThrow(InvalidUserIdException::new);
    }

    @Override
    public boolean editThoughts(long userId, String thoughts) {
        Optional<UserEntity> byId = this.userRepository.findById(userId);
        if (byId.isEmpty()) throw new InvalidUserIdException();

        UserEntity user = byId.get();
        String oldThoughts = user.getThoughts();
        if (oldThoughts != null) {
            if (oldThoughts.equals(thoughts)) return false;
        }

        user.setThoughts(thoughts);
        this.userRepository.save(user);
        return true;
    }

    /*public void likeRoute(RouteLikedEvent event) {
        long userId = event.getUserId();

        UserEntity user = this.userRepository.getById(userId);
        user.getRoutesLiked().add(event.getRouteLiked());
        UserEntity savedUser = userRepository.save(user);
        //event.setUserLiked(savedUser);
    }*/

    @Override
    public boolean login(UserLoginDTO loginDTO) {
        Optional<UserEntity> byUsername = this.userRepository.findByUsername(loginDTO.getUsername());
        if (byUsername.isEmpty()) return false;
        UserEntity user = byUsername.get();

        return passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
    }

    @Override
    public UserEntity getSingleUser(long userId) {
        var byId = this.userRepository.findById(userId);
        if (byId.isEmpty()) throw new InvalidUserIdException();
        return byId.get();
    }

    /*@Override
    public void likeARoute(long userId, RouteEntity route) {
        UserEntity user = this.userRepository.findById(userId).get();
        user.getRoutesLiked().add(route);
        this.userRepository.save(user);
    }*/

    Function<UserEntity, UserDetailsView> mapEntityToDetailsView = userEntity -> {
        UserDetailsView detailsView = this.mapper.map(userEntity, UserDetailsView.class);
        Set<UserRoleEnum> roleEnums = userEntity.getRoles().stream().map(UserRoleEntity::getRole).collect(Collectors.toSet());

        detailsView.setRoles(roleEnums);
        return detailsView;
    };
}
