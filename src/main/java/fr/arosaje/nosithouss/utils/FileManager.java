package fr.arosaje.nosithouss.utils;

import fr.arosaje.nosithouss.errors.NosithoussException;
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

    public static String saveImage(MultipartFile file) {
        try {
            if (!Files.exists(Path.of(uploadPath))) {
                Files.createDirectories(Path.of(uploadPath));
            }

            String uniqueFileName = UUID.randomUUID().toString();

            Path targetLocation = Path.of(uploadPath, uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return uniqueFileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new NosithoussException("Can't uplaod file");
        }
    }

    public static byte[] getImage(String imageUUID) throws IOException {
        Path imagePath = Paths.get(uploadPath, imageUUID);
        return Files.readAllBytes(imagePath);}
}
