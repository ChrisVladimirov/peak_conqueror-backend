package com.example.backend.services;

import com.example.backend.models.DTOs.CreateRouteDTO;
import com.example.backend.models.DTOs.EditRouteDTO;
import com.example.backend.models.DTOs.RouteDetailsView;
import com.example.backend.models.entities.Like;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RouteService {

    List<RouteDetailsView> getAll();

    RouteDetailsView getParticularRoute(long id);

    void createRoute(CreateRouteDTO createRouteDTO);

    //void editRoute(long id);//edit the entire route

    void deleteRoute(long id);

    //long getLikesForRoute(long routeId);

    void likeARoute(long routeId, long userId);

    void editRoute(long routeId, EditRouteDTO editRouteDTO);

    void removeALike(long routeId, long userId);

    Object getAllToughnessLevels();

    Set<Like> getLikesForRoute(long routeId);
}
