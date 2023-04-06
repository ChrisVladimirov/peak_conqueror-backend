package com.example.backend.services;

import com.example.backend.models.entities.UserRoleEntity;
import com.example.backend.models.enums.UserRoleEnum;

public interface UserRoleService {

    UserRoleEntity getRoleByRoleEnum(UserRoleEnum roleEnum);
}
