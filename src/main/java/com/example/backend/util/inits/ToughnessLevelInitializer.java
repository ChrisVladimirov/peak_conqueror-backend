package com.example.backend.util.inits;

import com.example.backend.models.entities.ToughnessLevelEntity;
import com.example.backend.models.enums.ToughnessEnum;
import com.example.backend.repositories.ToughnessLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ToughnessLevelInitializer implements CommandLineRunner {

    private final ToughnessLevelRepository toughnessLevelRepository;

    @Autowired
    public ToughnessLevelInitializer(ToughnessLevelRepository toughnessLevelRepository) {
        this.toughnessLevelRepository = toughnessLevelRepository;
    }

    @Override
    public void run(String... args) {
        if (toughnessLevelRepository.count() < 1) {
            ToughnessLevelEntity easyLevel = new ToughnessLevelEntity(ToughnessEnum.EASY);
            ToughnessLevelEntity moderateLevel = new ToughnessLevelEntity(ToughnessEnum.MODERATE);
            ToughnessLevelEntity averageLevel = new ToughnessLevelEntity(ToughnessEnum.AVERAGE);
            ToughnessLevelEntity difficultLevel = new ToughnessLevelEntity(ToughnessEnum.DIFFICULT);
            ToughnessLevelEntity alpineLevel = new ToughnessLevelEntity(ToughnessEnum.ALPINE);
            ToughnessLevelEntity challenging_alpineLevel = new ToughnessLevelEntity(ToughnessEnum.CHALLENGING_ALPINE);

            this.toughnessLevelRepository.save(easyLevel);
            this.toughnessLevelRepository.save(moderateLevel);
            this.toughnessLevelRepository.save(averageLevel);
            this.toughnessLevelRepository.save(difficultLevel);
            this.toughnessLevelRepository.save(alpineLevel);
            this.toughnessLevelRepository.save(challenging_alpineLevel);
        }
    }
}
