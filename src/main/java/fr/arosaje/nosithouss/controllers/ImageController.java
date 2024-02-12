package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.responses.AssetsRes;
import fr.arosaje.nosithouss.utils.FileManager;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/assets")
public class ImageController {

    private final FileManager fileManager;

    @GetMapping("/{imageUUID}")
    @Operation(summary = "Get an image by the uuid, no authentication needed")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageUUID) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(fileManager.getImage(imageUUID));
    }

    @PostMapping("/upload")
    @Operation(summary = "Upload file and get his uuid name")
    public AssetsRes uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return AssetsRes.builder().fileName(fileManager.saveImage(file).getName()).build();
    }
}
