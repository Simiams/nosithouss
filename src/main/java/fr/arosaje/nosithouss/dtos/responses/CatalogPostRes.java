package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.models.Post;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogPostRes extends PostRes {

    private String img;

    public CatalogPostRes(CatalogPost post) {
        super(post);
        this.img = post.getImg();
    }

}
