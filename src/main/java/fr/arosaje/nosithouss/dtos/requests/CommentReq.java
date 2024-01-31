package fr.arosaje.nosithouss.dtos.requests;

import fr.arosaje.nosithouss.models.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentReq {
    private Long postId;
    private String content;

    public Comment toComment() {
        return Comment.builder().content(content).build();
    }
}
