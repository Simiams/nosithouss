package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class    PostRes {

    private Long id;
    private RegisterRes author;
    private Date createdAt;
    private String content;
    private String title;
    private Post lastVersion;
    private int nbLike;
    private int nbDislike;

    public PostRes(Post post) {
        this.id = post.getId();
        this.author = new RegisterRes(post.getAuthor());
        this.createdAt = post.getCreatedAt();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.lastVersion = post.getLastVersion();
        this.nbLike = post.getNbLike();
        this.nbDislike = post.getNbDislike();
    }

}
