package com.example.backend.services.impl;

import com.example.backend.models.DTOs.UserWhoLikedView;
import com.example.backend.models.entities.Like;
import com.example.backend.repositories.LikeRepository;
import com.example.backend.services.LikeService;
import com.example.backend.util.exceptions.NoRouteLikedByThisUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    private final LikeRepository likeRepository;

    @Autowired
    public LikeServiceImpl(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    @Override
    public void like(long routeId, long userId) {
        this.likeRepository.save(new Like(routeId, userId));
    }

    @Override
    public void withdrawLike(long routeId, long userId) {
        Optional<Like> byRouteIdAndUserId = this.likeRepository.findByRouteIdAndUserId(routeId, userId);
        if (byRouteIdAndUserId.isEmpty()) throw new NoRouteLikedByThisUserException();

        Like like = byRouteIdAndUserId.get();

        this.likeRepository.deleteById(like.getId());
    }

    @Override
    public Set<Like> getAllLikesForRoute(long routeId) {
        List<Like> allByRouteId = this.likeRepository.findAllByRouteId(routeId);
        return new HashSet<>(allByRouteId);
    }
}
