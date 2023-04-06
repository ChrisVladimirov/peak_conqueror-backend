package com.example.backend.repositories;

import com.example.backend.models.entities.ToughnessLevelEntity;
import com.example.backend.models.enums.ToughnessEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToughnessLevelRepository extends JpaRepository<ToughnessLevelEntity, Long> {
    ToughnessLevelEntity findByToughness(ToughnessEnum toughnessEnum);
}
