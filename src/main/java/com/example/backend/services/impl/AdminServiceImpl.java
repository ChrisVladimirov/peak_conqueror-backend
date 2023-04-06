package com.example.backend.services.impl;

import com.example.backend.models.DTOs.UserDetailsView;
import com.example.backend.models.entities.UserEntity;
import com.example.backend.models.entities.UserRoleEntity;
import com.example.backend.models.enums.UserRoleEnum;
import com.example.backend.repositories.UserRepository;
import com.example.backend.repositories.UserRoleRepository;
import com.example.backend.services.AdminService;
import com.example.backend.util.exceptions.InvalidUserIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    private final UserRoleRepository rolesRepository;
    private final UserServiceImpl userService;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository, UserRoleRepository rolesRepository, UserServiceImpl userService) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.userService = userService;
    }

    @Override
    public void promote(long id) {
        UserRoleEntity adminRole = this.rolesRepository.findByRoleAndId(UserRoleEnum.ADMIN, 2);

        UserEntity userToPromote = validateUser.apply(this.userRepository.findById(id));
        userToPromote.addRole(adminRole);

        this.userRepository.save(userToPromote);
    }

    @Override
    public void demote(long id) {
        UserEntity adminToDemote = validateUser.apply(this.userRepository.findById(id));
        adminToDemote.removeRole(UserRoleEnum.ADMIN);
        this.userRepository.save(adminToDemote);
    }

    @Override
    public List<UserDetailsView> getAllAdmins() {
        return this.userRepository.findByRolesContains(UserRoleEnum.ADMIN).stream().map(this.userService.mapEntityToDetailsView).toList();
    }

    @Override
    public UserDetailsView getParticularAdmin(long adminId) {
        return this.getAllAdmins().stream().filter(admin -> admin.getId() == adminId).findFirst().orElseThrow(InvalidUserIdException::new);
    }

    Function<Optional<UserEntity>, UserEntity> validateUser = optionalUser -> {
        if (optionalUser.isEmpty()) throw new InvalidUserIdException();
        return optionalUser.get();
    };
}
