package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.client.TrefleClient;
import fr.arosaje.nosithouss.dtos.requests.TrefleReq;
import fr.arosaje.nosithouss.enums.EFlag;
import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.models.Flag;
import fr.arosaje.nosithouss.repositories.FlagRepository;
import fr.arosaje.nosithouss.repositories.ImageRepository;
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
    private final ImageRepository imageRepository;
    private final FileManager fileManager;

    public TrefleService(TrefleClient trefleClient, PostRepository postRepository, FlagRepository flagRepository, ImageRepository imageRepository, FileManager fileManager) {
        this.trefleClient = trefleClient;
        this.postRepository = postRepository;
        this.flagRepository = flagRepository;
        this.imageRepository = imageRepository;
        this.fileManager = fileManager;
    }

    public void savePlants() {
        Flag flagPage = flagRepository.findByKey(EFlag.LASTPAGE.getKey());
        String nextPage = flagPage == null ? "/api/v1/plants?page=1" : flagPage.getValue(); //todo aplication.yml
        while (!nextPage.isEmpty()) {
            Pair<List<TrefleReq>, String> res = trefleClient.getPlants(nextPage);
            nextPage = res.getSecond();
            res.getFirst().stream()
                    .map(this::convertToCatalogPost)
                    .forEach(this::secureSave);
            flagRepository.save(Flag.builder()
                                        .key(EFlag.LASTPAGE.getKey())
                                        .value(nextPage)
                                        .date(now())
                                        .build());
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

    private void secureSave(CatalogPost post) {
        if (post.getTitle() != null && post.getImages() != null) {
            postRepository.save(post);
        }
    }
}
