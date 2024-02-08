package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.CatalogPost;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CatalogPostRes extends PostRes {

    private List<String> img;

    public CatalogPostRes(CatalogPost post) {
        super(post);
        this.img = post.getImages();
    }
}
