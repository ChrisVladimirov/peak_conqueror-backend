package com.example.backend.services.impl;

import com.example.backend.models.DTOs.CreateRouteDTO;
import com.example.backend.models.DTOs.EditRouteDTO;
import com.example.backend.models.DTOs.RouteDetailsView;
import com.example.backend.models.DTOs.UserWhoLikedView;
import com.example.backend.models.entities.*;
import com.example.backend.models.enums.ToughnessEnum;
import com.example.backend.repositories.*;
import com.example.backend.services.LikeService;
import com.example.backend.services.RouteService;
import com.example.backend.services.UserService;
import com.example.backend.util.exceptions.RouteAlreadyPresentException;
import com.example.backend.util.exceptions.RouteNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private ModelMapper mapper;
    private final Function<RouteEntity, RouteDetailsView> routeEntityToDetailsView = routeEntity -> {
        RouteDetailsView detailsView = this.mapper.map(routeEntity, RouteDetailsView.class);

        Set<Like> usersViews = /*routeEntity.getUsersWhoLiked().stream()
                .map(u -> new UserWhoLikedView(u.getId(), u.getUsername()))
                .collect(Collectors.toSet())*/
                this.likeService.getAllLikesForRoute(routeEntity.getId());
        detailsView.setUsersWhoLiked(usersViews);

        return detailsView;
    };

    private final ToughnessLevelRepository toughnessLevelRepository;
    private final PictureRepository pictureRepository;
    private final CoordinatesFileRepository coordinatesFileRepository;
    private final UserService userService;
    private LikeService likeService;

    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ModelMapper mapper,
                            ToughnessLevelRepository toughnessLevelRepository, PictureRepository pictureRepository,
                            CoordinatesFileRepository coordinatesFileRepository,
                            UserService userService, LikeService likeService) {
        this.routeRepository = routeRepository;
        this.mapper = mapper;
        this.toughnessLevelRepository = toughnessLevelRepository;
        this.pictureRepository = pictureRepository;
        this.coordinatesFileRepository = coordinatesFileRepository;
        this.userService = userService;
        this.likeService = likeService;
    }

    @Override
    public List<RouteDetailsView> getAll() {
        return this.routeRepository.findAll().stream().map(routeEntityToDetailsView).toList();
    }

    @Override
    public RouteDetailsView getParticularRoute(long id) {
        return this.routeRepository.findById(id).map(routeEntityToDetailsView).orElseThrow(RouteNotFoundException::new);
    }

    @Override
    public void createRoute(CreateRouteDTO createRouteDTO) {
        RouteEntity route = this.mapper.map(createRouteDTO, RouteEntity.class);
        if (this.routeRepository.findByName(route.getName()).isPresent()) throw new RouteAlreadyPresentException();

        String toughnessEnum = createRouteDTO.getToughness();
        ToughnessLevelEntity byToughness = this.toughnessLevelRepository.findByToughness(ToughnessEnum.valueOf(toughnessEnum));
        route.setToughnessLevel(byToughness);

        Set<PictureEntity> pictures = createRouteDTO
                .getPictureUrls()
                .stream()
                .map(this.pictureRepository::findByUrl)
                .collect(Collectors.toSet());

        route.setPictures(pictures);

        String coordinatesFileName = composeCoordinatesFileEntityName(route.getName());
        String coordinatesUrl = createRouteDTO.getCoordinatesUrl();
        if (coordinatesUrl != null && !coordinatesUrl.equals("")) {
            CoordinatesFileEntity coordinatesFileEntity = this.coordinatesFileRepository
                    .save(new CoordinatesFileEntity(coordinatesFileName, coordinatesUrl));
            route.setCoordinates(coordinatesFileEntity);
        } else {
            route.setCoordinates(null);
        }

        this.routeRepository.save(route);
    }

    @Override
    public void deleteRoute(long id) {
        this.routeRepository.deleteById(id);
    }

    @Override
    public void likeARoute(long routeId, long userId) {
        this.likeService.like(routeId, userId);
    }

    @Override
    public void removeALike(long routeId, long userId) {
        this.likeService.withdrawLike(routeId, userId);
    }
    /*@Override
    public void likeARoute(long routeId, long userId) {
        Optional<RouteEntity> byId = this.routeRepository.findById(routeId);
        if (byId.isEmpty()) throw new RouteNotFoundException();

        RouteEntity route = byId.get();

        UserEntity user = this.userService.getSingleUser(userId);

        route.getUsersWhoLiked().add(user);
        this.routeRepository.save(route);

        this.userService.likeARoute(userId, route);
    }*/

    @Override
    public void editRoute(long routeId, EditRouteDTO editRouteDTO) {
        Optional<RouteEntity> byId = this.routeRepository.findById(routeId);
        if (byId.isEmpty()) throw new RouteNotFoundException();
        RouteEntity routeEntity = byId.get();

        routeEntity.setName(editRouteDTO.getName());
        routeEntity.setItinerary(editRouteDTO.getItinerary());
        routeEntity.setToughnessLevel((this.toughnessLevelRepository
                .findByToughness(editRouteDTO.getToughness())));
        routeEntity.setCoordinates((this.coordinatesFileRepository
                .getByUrl(editRouteDTO.getCoordinatesUrl())));
        routeEntity.setDuration(editRouteDTO.getDuration());
        routeEntity.setPictures(editRouteDTO.getPictureUrls()
                .stream().map(this.pictureRepository::findByUrl).collect(Collectors.toSet()));
        this.routeRepository.save(routeEntity);
    }
    /*@Override
    public void removeALike(long routeId, long userId) {
        Optional<RouteEntity> byId = this.routeRepository.findById(routeId);
        if (byId.isEmpty()) throw new RouteNotFoundException();
        RouteEntity routeEntity = byId.get();

        var user = this.userService.getSingleUser(userId);

        routeEntity.getUsersWhoLiked().remove(user);
        this.routeRepository.save(routeEntity);
    }*/

    @Override
    public Map<ToughnessEnum, String[]> getAllToughnessLevels() {
        var levels = new HashMap<ToughnessEnum, String[]>();
        for (ToughnessEnum toughnessEnum : ToughnessEnum.values()) {
            levels.put(toughnessEnum, new String[]{toughnessEnum.getDescription(), toughnessEnum.getSimpleName()});
        }
        return levels;
    }

    @Override
    public Set<Like> getLikesForRoute(long routeId) {
        return this.likeService.getAllLikesForRoute(routeId);
    }

    private String composeCoordinatesFileEntityName(String routeName) {
        return String.format("%s_coordinates", routeName);
    }
}
