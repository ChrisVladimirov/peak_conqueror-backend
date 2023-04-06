package com.example.backend.models.entities;

import com.example.backend.models.enums.ToughnessEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "toughness_levels")
public class ToughnessLevelEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ToughnessEnum toughness;

    public ToughnessLevelEntity() {}

    public ToughnessLevelEntity(ToughnessEnum toughness) {
        this.setToughness(toughness);
    }

    public ToughnessEnum getToughness() {
        return toughness;
    }

    public ToughnessLevelEntity setToughness(ToughnessEnum toughness) {
        this.toughness = toughness;
        return this;
    }
}
