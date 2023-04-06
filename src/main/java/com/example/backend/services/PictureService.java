package com.example.backend.services;

import com.example.backend.models.DTOs.EditPictureDTO;
import com.example.backend.models.DTOs.PictureCreateDTO;
import com.example.backend.models.entities.PictureEntity;

import java.util.List;

public interface PictureService {

    List<PictureEntity> getAll();

    void addPicture(PictureCreateDTO createDTO);

    void editPicture(long pictureId, EditPictureDTO editDTO);

    void deletePicture(long pictureId);

    PictureEntity getParticularPicture(long pictureId);
}
