package com.example.backend.services;

import com.example.backend.models.DTOs.UserWhoLikedView;
import com.example.backend.models.entities.Like;

import java.util.Set;

public interface LikeService {

    void like(long routeId, long userId);

    void withdrawLike(long routeId, long userId);

    Set<Like> getAllLikesForRoute(long routeId);
}
