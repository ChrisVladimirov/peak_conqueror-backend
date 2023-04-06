package com.example.backend.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "routes_users")
public class Like extends BaseEntity {

    private long routeId;

    private long userId;

    public Like() {
    }

    public Like(long routeId, long userId) {
        this.routeId = routeId;
        this.userId = userId;
    }

    public long getRouteId() {
        return routeId;
    }

    public Like setRouteId(long routeId) {
        this.routeId = routeId;
        return this;
    }

    public long getUserId() {
        return userId;
    }

    public Like setUserId(long userId) {
        this.userId = userId;
        return this;
    }
}
