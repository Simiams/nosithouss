package fr.arosaje.nosithouss.utils;

import fr.arosaje.nosithouss.errors.NosithoussException;
import fr.arosaje.nosithouss.models.Image;
import fr.arosaje.nosithouss.repositories.ImageRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileManager {
    private static String uploadPath = "uploads"; //todo properties file
    private final ImageRepository imageRepository;

    public FileManager(ImageRepository imageRepository) {this.imageRepository = imageRepository;}

    public static byte[] getLocallyImage(String imageUUID) throws IOException {
        Path imagePath = Paths.get(uploadPath, imageUUID);
        return Files.readAllBytes(imagePath);
    }

    public byte[] getImage(String imageUUID) {
        try {
            return imageRepository.findByName(imageUUID).getData();
        } catch (Exception e) {
            throw new NosithoussException("Image not found: " + e.getMessage());
        }
    }

    public Image saveImage(MultipartFile file) throws IOException {
        String uniqueFileName = UUID.randomUUID().toString();
        return imageRepository.save(Image.builder().data(file.getBytes()).name(uniqueFileName).build());
    }
    public Image saveImage(byte[] file) {
        String uniqueFileName = UUID.randomUUID().toString();
        return imageRepository.save(Image.builder().data(file).name(uniqueFileName).build());
    }
}
