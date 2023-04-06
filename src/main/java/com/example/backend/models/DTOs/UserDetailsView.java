package com.example.backend.models.DTOs;

import com.example.backend.models.enums.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsView {

    @NotNull
    private long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String username;

    @NotBlank
    private String email;

    @NotBlank
    private String thoughts;

    private Set<UserRoleEnum> roles;

    public UserDetailsView() {
        this.roles = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public UserDetailsView setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDetailsView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDetailsView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserDetailsView setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDetailsView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getThoughts() {
        return thoughts;
    }

    public UserDetailsView setThoughts(String thoughts) {
        this.thoughts = thoughts;
        return this;
    }

    public Set<UserRoleEnum> getRoles() {
        return roles;
    }

    public UserDetailsView setRoles(Set<UserRoleEnum> roles) {
        this.roles = roles;
        return this;
    }
}
