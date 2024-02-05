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
        URL url = new URL("http://localhost");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        byte[] fileBytes = file.getBytes();
        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(fileBytes);
        outputStream.close();
        outputStream.close();
        int responseCode = connection.getResponseCode();
        connection.disconnect();
    }
}
