package fr.arosaje.nosithouss.utils;

import fr.arosaje.nosithouss.errors.NosithoussException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Component
public class FileManager {
    private static String uploadPath = "here"; //todo properties file

    //todo: https://www.bezkoder.com/spring-boot-file-upload/
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
            throw new NosithoussException();
        }
    }
}
