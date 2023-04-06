package com.example.backend.services;

import com.example.backend.models.DTOs.UserDetailsView;
import com.example.backend.models.DTOs.UserLoginDTO;
import com.example.backend.models.DTOs.UserRegisterDTO;
import com.example.backend.models.entities.RouteEntity;
import com.example.backend.models.entities.UserEntity;

import java.util.List;

public interface UserService {

    boolean register(UserRegisterDTO registerDTO);

    List<UserDetailsView> getAllUsers();

    UserDetailsView getParticularUser(String username);

    UserDetailsView getParticularUser(long id);

    boolean editThoughts(long userId, String newThoughts);

    boolean login(UserLoginDTO loginDTO);

    UserEntity getSingleUser(long userId);

    //void likeARoute(long userId, RouteEntity routeId);
}
