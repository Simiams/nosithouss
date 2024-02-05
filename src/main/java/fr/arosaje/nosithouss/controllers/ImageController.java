package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.utils.FileManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/assets")
public class ImageController {

    @GetMapping("/{imageUUID}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageUUID) {
        try {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(FileManager.getImage(imageUUID));
        } catch (IOException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
