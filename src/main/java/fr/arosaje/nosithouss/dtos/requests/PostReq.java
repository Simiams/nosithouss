package fr.arosaje.nosithouss.dtos.requests;

import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.models.GuardingPost;
import fr.arosaje.nosithouss.models.Image;
import fr.arosaje.nosithouss.models.Post;
import lombok.Builder;
import lombok.Getter;

import java.awt.Point;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
public class PostReq {

    private String type;
    private String content;
    private String title;
    private Post lastVersion;
    private int nbLike;
    private int nbDislike;
    private String img;
    private Point coordinates;
    private Timestamp guardingAt;
    private Timestamp endGuardingAt;

    public Post toPost() {
        return Post.builder().content(content).title(title).lastVersion(lastVersion).nbLike(nbLike).nbDislike(nbDislike).build();
    }

    public CatalogPost toCatalogPost() {
        return CatalogPost.builder().content(content).title(title).lastVersion(lastVersion).nbLike(nbLike).nbDislike(nbDislike).images(List.of(img)).build();
    }

    public GuardingPost toGuardingPost() {
        return GuardingPost.builder().content(content).title(title).lastVersion(lastVersion).nbLike(nbLike).nbDislike(nbDislike).images(List.of(img)).guardingAt(guardingAt).endGuardingAt(endGuardingAt).coordinateX(coordinates.x).coordinateY(coordinates.y).build();
    }
}
