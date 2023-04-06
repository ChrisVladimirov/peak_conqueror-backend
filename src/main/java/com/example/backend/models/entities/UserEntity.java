package com.example.backend.models.entities;

import com.example.backend.models.enums.UserRoleEnum;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "TINYTEXT")
    private String thoughts;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<UserRoleEntity> roles;

    //@ManyToMany(fetch = FetchType.EAGER)
    //private Set<RouteEntity> routesLiked;

    public UserEntity() {
        this.roles = new HashSet<>();
        //this.routesLiked = new HashSet<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getThoughts() {
        return thoughts;
    }

    public UserEntity setThoughts(String thoughts) {
        this.thoughts = thoughts;
        return this;
    }

    public Set<UserRoleEntity> getRoles() {
        return roles;
    }

    public void addRole(UserRoleEntity role) {
        this.getRoles().add(role);
    }

    public void removeRole(UserRoleEnum role) {
        this.getRoles().removeIf(userRoleEntity -> userRoleEntity.getRole().equals(role));
    }

    /*public Set<RouteEntity> getRoutesLiked() {
        return routesLiked;
    }*/

    /*public UserEntity setRoutesLiked(Set<RouteEntity> routesLiked) {
        this.routesLiked = routesLiked;
        return this;
    }*/
}
