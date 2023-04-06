package com.example.backend.repositories;

import com.example.backend.models.entities.UserRoleEntity;
import com.example.backend.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Long> {
    UserRoleEntity findByRoleAndId(UserRoleEnum roleEnum, long id);

    Optional<UserRoleEntity> findByRole(UserRoleEnum roleEnum);
}
