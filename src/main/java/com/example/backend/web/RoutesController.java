package com.example.backend.web;

import com.example.backend.models.DTOs.CreateRouteDTO;
import com.example.backend.models.DTOs.EditRouteDTO;
import com.example.backend.models.DTOs.RouteDetailsView;
import com.example.backend.services.RouteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/routes")
public class RoutesController {

    private final RouteService routeService;

    @Autowired
    public RoutesController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<RouteDetailsView>> getAllRoutes() {
        List<RouteDetailsView> routes = this.routeService.getAll();
        return ResponseEntity.ok(routes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RouteDetailsView> getParticularRoute(@PathVariable(name = "id") long routeId) {
        RouteDetailsView particularRoute = this.routeService.getParticularRoute(routeId);
        return ResponseEntity.ok(particularRoute);
    }

    @GetMapping("/toughness-levels/all")
    public ResponseEntity<?> getAllToughnessLevels() {
        return ResponseEntity.ok(this.routeService.getAllToughnessLevels());
    }

    @GetMapping("/{id}/likes")
    public ResponseEntity<?> getAllLikesPerRoute(@PathVariable(name = "id") long routeId) {
        var likes = this.routeService.getLikesForRoute(routeId);
        return ResponseEntity.ok(likes);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<?> likeARoute(@PathVariable(name = "id") long routeId,
                                        @RequestParam(name = "user") long userId) {
        this.routeService.likeARoute(routeId, userId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/removeLike")
    public ResponseEntity<?> removeALike(@PathVariable(name = "id") long routeId,
                                         @RequestParam(name = "user") long userId) {
        this.routeService.removeALike(routeId, userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> createRoute(@RequestBody @Valid CreateRouteDTO createRouteDTO) {
        this.routeService.createRoute(createRouteDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{routeId}/edit")
    @PreAuthorize("hasAnyAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> editRoute(@RequestBody @Valid EditRouteDTO editRouteDTO, @PathVariable long routeId) {
        this.routeService.editRoute(routeId, editRouteDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<?> deleteRoute(@RequestParam(name = "routeId") long routeId) {
        this.routeService.deleteRoute(routeId);
        return ResponseEntity.noContent().build();
    }
}
