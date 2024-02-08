package fr.arosaje.nosithouss.client;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.arosaje.nosithouss.dtos.requests.TrefleReq;
import fr.arosaje.nosithouss.errors.NosithoussException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class TrefleClient {
    private final WebClient webCLient;
    private final ObjectMapper objectMapper;
    private String baseUrl = "https://trefle.io";
    private String tokenUrl = "&token=q5hC4vxNw4lWucbXgIUcnYirPzAkkdRV6lJDOdILJRo";

    public TrefleClient(WebClient.Builder webClientBuilder, ObjectMapper objectMapper) {
        this.webCLient = webClientBuilder.baseUrl(baseUrl).build();
        this.objectMapper = objectMapper;
    }

    public Pair<List<TrefleReq>, String> getPlants(String uri) {
        JsonNode trefleRes = webCLient.get()
                .uri(uri + tokenUrl)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();
        List<TrefleReq> trefleReqs = new ArrayList<>();
        trefleRes.get("data").forEach(trefle -> trefleReqs.add(objectMapper.convertValue(trefle, TrefleReq.class)));
        return Pair.of(trefleReqs,
                      trefleRes.get("links").has("next") ? trefleRes.get("links").get("next").asText("") : "");
    }
    public byte[] getImage(String url) {
        try {
            return webCLient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();
        } catch (Exception e) {
            log.error("[NOSITHOUSS][TrefleClient] Error when try to get Image: {}", e.getMessage());
            return null;
        }
    }
}
