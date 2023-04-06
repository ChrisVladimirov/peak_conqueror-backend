package com.example.backend.services.impl;

import com.example.backend.models.entities.UserRoleEntity;
import com.example.backend.models.enums.UserRoleEnum;
import com.example.backend.repositories.UserRoleRepository;
import com.example.backend.services.UserRoleService;
import com.example.backend.util.exceptions.UserRoleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository roleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public UserRoleEntity getRoleByRoleEnum(UserRoleEnum roleEnum) {
        return this.roleRepository.findByRole(roleEnum).orElseThrow(UserRoleNotFoundException::new);
    }
}
