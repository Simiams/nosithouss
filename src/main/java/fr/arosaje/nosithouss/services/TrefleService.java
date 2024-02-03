package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.client.TrefleClient;
import fr.arosaje.nosithouss.dtos.requests.TrefleReq;
import fr.arosaje.nosithouss.enums.EFlag;
import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.models.Flag;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.repositories.FlagRepository;
import fr.arosaje.nosithouss.repositories.PostRepository;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrefleService {
    private final TrefleClient trefleClient;
    private final PostRepository postRepository;
    private final FlagRepository flagRepository;

    public TrefleService(TrefleClient trefleClient, PostRepository postRepository, FlagRepository flagRepository) {
        this.trefleClient = trefleClient;
        this.postRepository = postRepository;
        this.flagRepository = flagRepository;
    }

    public void savePlants() {
        Flag flagPage = flagRepository.findByKey(EFlag.LASTPAGE.getKey());
        String nextPage = flagPage == null ? "/api/v1/plants?page=1" : flagPage.getValue();
        while (!nextPage.isEmpty()) {
            Pair<List<TrefleReq>, String> res = trefleClient.getPlants(nextPage);
            nextPage = res.getSecond();
            res.getFirst().stream()
                    .map(this::convertToCatalogPost)
                    .forEach(this::secureSave);
            flagRepository.save(Flag.builder()
                                        .key(EFlag.LASTPAGE.getKey())
                                        .value(nextPage)
                                        .date(new Date())
                                        .build());
        }
    }

    private Post convertToCatalogPost(TrefleReq trefleReq) {
        return CatalogPost.builder()
                .title(trefleReq.getCommonName())
                .img(trefleReq.getImageUrl())
                .createdAt(new Date())
                .build();
    }

    private void secureSave(Post post) {
        if (post.getTitle() != null)
            postRepository.save(post);
    }
}
