package com.example.backend.repositories;

import com.example.backend.models.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByRouteIdAndUserId(long routeId, long userId);

    List<Like> findAllByRouteId(long routeId);
}
