package fr.arosaje.nosithouss.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
@Component
public class ApacheClient {

    @Value("${apache.uploadUrl}")
    private static String apacheUrl;

    public static void uploadImage(MultipartFile file) throws IOException {
        URL url = new URL("http://localhost/upload");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        try (OutputStream outputStream = connection.getOutputStream()) {
            byte[] fileBytes = file.getBytes();
            outputStream.write(fileBytes);
        }
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);
        connection.disconnect();
    }
}
