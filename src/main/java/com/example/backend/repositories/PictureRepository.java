package com.example.backend.repositories;

import com.example.backend.models.entities.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<PictureEntity, Long> {
    PictureEntity findByUrl(String url);
}
