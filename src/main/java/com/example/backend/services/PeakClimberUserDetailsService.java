package com.example.backend.services;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class PeakClimberUserDetailsService implements UserDetails {

    private final String firstName;
    private final String lastName;
    private final String username;
    private final String password;
    private final String email;
    private final Collection<GrantedAuthority> authorities;

    public PeakClimberUserDetailsService(String firstName, String lastName,
                                         String username, String password, String email,
                                         Collection<GrantedAuthority> authorities) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
