package com.example.backend.util.inits;

import com.example.backend.models.entities.UserRoleEntity;
import com.example.backend.models.enums.UserRoleEnum;
import com.example.backend.repositories.UserRepository;
import com.example.backend.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserRolesInitializer implements CommandLineRunner {
    private final UserRoleRepository userRoleRepository;
    private final UserRepository userRepository;
    private final OwnerInitializer ownerInitializer;

    @Autowired
    public UserRolesInitializer(UserRoleRepository userRoleRepository, UserRepository userRepository, OwnerInitializer ownerInitializer) {
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.ownerInitializer = ownerInitializer;
    }

    @Override
    public void run(String... args) {
        if (this.userRoleRepository.count() == 0) {
            UserRoleEntity owner = new UserRoleEntity(UserRoleEnum.OWNER);
            UserRoleEntity admin = new UserRoleEntity(UserRoleEnum.ADMIN);
            UserRoleEntity member = new UserRoleEntity(UserRoleEnum.MEMBER);

            UserRoleEntity savedOwner = this.userRoleRepository.save(owner);
            this.userRoleRepository.save(admin);
            this.userRoleRepository.save(member);

            this.ownerInitializer.initOwner(savedOwner);
        }
    }
}
