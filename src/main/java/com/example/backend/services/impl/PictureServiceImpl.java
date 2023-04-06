package com.example.backend.services.impl;

import com.example.backend.models.DTOs.EditPictureDTO;
import com.example.backend.models.DTOs.PictureCreateDTO;
import com.example.backend.models.entities.PictureEntity;
import com.example.backend.repositories.PictureRepository;
import com.example.backend.services.PictureService;
import com.example.backend.util.exceptions.PictureNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<PictureEntity> getAll() {
        return this.pictureRepository.findAll();
    }

    @Override
    public PictureEntity getParticularPicture(long pictureId) {
        Optional<PictureEntity> byId = this.pictureRepository.findById(pictureId);
        if (byId.isEmpty()) throw new PictureNotFoundException();
        return byId.get();
    }

    @Override
    public void addPicture(PictureCreateDTO createDTO) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setTitle(createDTO.getTitle()).setUrl(createDTO.getUrl());
        this.pictureRepository.save(pictureEntity);
    }

    @Override
    public void editPicture(long pictureId, EditPictureDTO editDTO) {
        Optional<PictureEntity> byId = this.pictureRepository.findById(pictureId);
        if (byId.isEmpty()) throw new PictureNotFoundException();

        PictureEntity pictureEntity = byId.get();
        pictureEntity.setTitle(editDTO.getTitle());
        pictureEntity.setUrl(editDTO.getUrl());

        this.pictureRepository.save(pictureEntity);
    }

    @Override
    public void deletePicture(long pictureId) {
        Optional<PictureEntity> byId = this.pictureRepository.findById(pictureId);
        if (byId.isEmpty()) throw new PictureNotFoundException();
        this.pictureRepository.deleteById(pictureId);
    }
}
