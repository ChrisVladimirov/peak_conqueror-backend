package com.example.backend.repositories;

import com.example.backend.models.entities.CoordinatesFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordinatesFileRepository extends JpaRepository<CoordinatesFileEntity, Long> {
    CoordinatesFileEntity getByUrl(String coordinatesUrl);
}
