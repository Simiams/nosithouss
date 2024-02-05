package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.enums.EPostType;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.utils.PostUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PostRes {

    private EPostType type;
    private Long id;
    private String authorUserName;
    private Date createdAt;
    private String content;
    private String title;
    private Post lastVersion;
    private int nbLike;
    private int nbDislike;

    public PostRes(Post post) {
        this.type = PostUtils.getEPostByPost(post);
        this.id = post.getId();
        this.authorUserName = post.getAuthor().getUsername();
        this.createdAt = post.getCreatedAt();
        this.content = post.getContent();
        this.title = post.getTitle();
        this.lastVersion = post.getLastVersion();
        this.nbLike = post.getNbLike();
        this.nbDislike = post.getNbDislike();
    }
}
