package com.example.backend.models.DTOs;

import com.example.backend.util.validation.PasswordMatches;
import com.example.backend.util.validation.ValidEmail;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatches
public class UserRegisterDTO {
    @NotBlank(message = "First name should not be blank!")
    @Size(min = 3, max = 20, message = "First name should be between 3 and 20 characters!")
    private String firstName;

    @NotBlank(message = "Last name should not be blank!")
    @Size(min = 3, max = 20, message = "Last name should be between 3 and 20 characters!")
    private String lastName;

    @NotBlank(message = "Username should be between 5 and 20 characters.")
    @Size(min = 5, max = 20, message = "Username size must be between 5 and 20 characters!")
    private String username;

    @NotBlank(message = "Email should not be blank!")
    @ValidEmail
    private String email;

    @NotBlank(message = "Password should not be empty!")
    @Size(min = 7, max = 15, message = "Use a strong password between 7 and 15 characters!")
    private String password;

    @NotBlank(message = "Password should not be empty!")
    @Size(min = 7, max = 15, message = "Repeat your password!")
    private String confirmPassword;

    public UserRegisterDTO() {}

    public UserRegisterDTO(String firstName, String lastName, String username,
                           String email, String password, String confirmPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserRegisterDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterDTO setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
