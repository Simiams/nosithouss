package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.client.TrefleClient;
import fr.arosaje.nosithouss.dtos.requests.TrefleReq;
import fr.arosaje.nosithouss.enums.EFlag;
import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.models.Flag;
import fr.arosaje.nosithouss.repositories.FlagRepository;
import fr.arosaje.nosithouss.repositories.PostRepository;
import fr.arosaje.nosithouss.utils.FileManager;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;

import static fr.arosaje.nosithouss.utils.Utils.now;

@Service
public class TrefleService {
    private final TrefleClient trefleClient;
    private final PostRepository postRepository;
    private final FlagRepository flagRepository;
    private final FileManager fileManager;
    private int currentNumber = 0;
    private String lastCommonName;

    public TrefleService(TrefleClient trefleClient, PostRepository postRepository, FlagRepository flagRepository, FileManager fileManager) {
        this.trefleClient = trefleClient;
        this.postRepository = postRepository;
        this.flagRepository = flagRepository;
        this.fileManager = fileManager;
    }

    public void savePlants(int limit) {
        Flag flagPage = flagRepository.findByKey(EFlag.LAST_PAGE.getKey());
        String nextPage = flagPage == null ? "/api/v1/plants?page=1" : flagPage.getValue(); //todo aplication.yml
        while (!nextPage.isEmpty() && (limit == 0 || limit > currentNumber)) {
            Pair<List<TrefleReq>, String> res = trefleClient.getPlants(nextPage);
            res.getFirst().stream()
                    .map(this::convertToCatalogPost)
                    .map(catalogPost -> (limit == 0 || limit > currentNumber) ? secureSave(catalogPost, res.getSecond()) : null).toList().getLast();
        }
    }

    private CatalogPost convertToCatalogPost(TrefleReq trefleReq) {
        byte[] newImage = trefleClient.getImage(trefleReq.getImageUrl());
        return CatalogPost.builder()
                .title(trefleReq.getCommonName())
                .images(newImage == null ? null : List.of(fileManager.saveImage(newImage).getName()))
                .createdAt(now())
                .build();
    }

    private CatalogPost secureSave(CatalogPost post, String nextPage) {
        if (post != null && post.getTitle() != null && post.getImages() != null) {
            currentNumber += 1;
            lastCommonName = post.getTitle();
            secureSaveFlags(nextPage);
            return postRepository.save(post);
        }
        return null;
    }

    private void secureSaveFlags(String nextPage) {
        flagRepository.save(Flag.builder().key(EFlag.LAST_PAGE.getKey())
                                    .value(nextPage)
                                    .date(now()).build());
        flagRepository.save(Flag.builder().key(EFlag.LAST_COMMON_NAME.getKey())
                                    .value(lastCommonName)
                                    .date(now()).build());
        flagRepository.save(Flag.builder().key(EFlag.LAST_EXTRACTED_NUMBER.getKey())
                                    .value(Integer.toString(currentNumber))
                                    .date(now()).build());
    }
}
