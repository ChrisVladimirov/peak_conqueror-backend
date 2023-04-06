package com.example.backend.web;

import com.example.backend.models.DTOs.EditPictureDTO;
import com.example.backend.models.DTOs.PictureCreateDTO;
import com.example.backend.models.entities.PictureEntity;
import com.example.backend.services.PictureService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/pictures")
public class PictureController {

    private final PictureService pictureService;

    @Autowired
    public PictureController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/all")
    public ResponseEntity<?> viewAllPictures() {
        List<PictureEntity> all = this.pictureService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> viewParticularPicture(@PathVariable(name = "id") long pictureId) {
        PictureEntity picture = this.pictureService.getParticularPicture(pictureId);
        return ResponseEntity.ok(picture);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addPicture(@RequestBody @Valid PictureCreateDTO createDTO) {
        this.pictureService.addPicture(createDTO);
        return new ResponseEntity<>(CREATED);
    }

    @PutMapping("/{id}/edit")
    public ResponseEntity<?> editPictureUrl(@PathVariable(name = "id") long pictureId,
                                            @RequestBody @Valid EditPictureDTO editDTO) {
        this.pictureService.editPicture(pictureId, editDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deletePicture(@PathVariable(name = "id") long pictureId) {
        this.pictureService.deletePicture(pictureId);
        return ResponseEntity.noContent().build();
    }
}
