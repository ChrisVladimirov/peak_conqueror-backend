package com.example.backend.repositories;

import com.example.backend.models.entities.UserEntity;
import com.example.backend.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByRolesContains(UserRoleEnum roleEnum);
}
