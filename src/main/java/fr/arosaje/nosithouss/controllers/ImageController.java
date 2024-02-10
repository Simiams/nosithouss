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

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/assets")
public class ImageController {

    private final FileManager fileManager;

    @GetMapping("/{imageUUID}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageUUID) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileManager.getImage(imageUUID));
    }
}
