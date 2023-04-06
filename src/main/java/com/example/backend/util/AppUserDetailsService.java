package com.example.backend.util;

import com.example.backend.models.entities.UserEntity;
import com.example.backend.models.entities.UserRoleEntity;
import com.example.backend.repositories.UserRepository;
import com.example.backend.services.PeakClimberUserDetailsService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AppUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .map(this::map)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with username " + username + "was not found!"));
    }

    private UserDetails map(UserEntity user) {
        return new PeakClimberUserDetailsService(
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getRoles()
                        .stream().map(this::mapRole).toList()
        );
    }

    private GrantedAuthority mapRole(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority(/*"ROLE_" + */userRole.getRole().name());
    }
}
