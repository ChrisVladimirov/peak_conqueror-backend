package com.example.backend.services;

import com.example.backend.models.DTOs.UserDetailsView;

import java.util.List;

public interface AdminService {

    /**
     *
     * @param id id of the member to be promoted to admin
     */
    void promote(long id);

    /**
     *
     * @param id id of the admin to be demoted to member
     */
    void demote(long id);

    /**
     *
     * @return a complete List of all users with admin privileges.
     * Can be used by the Owner to help them monitor and manage user rights
     * across the application.
     */
    List<UserDetailsView> getAllAdmins();

    /**
     *
     * @param adminId long primitive value representing the if of the user
     * @return a UserDetailsView object with some data about the admin requested
     */
    UserDetailsView getParticularAdmin(long adminId);
}
