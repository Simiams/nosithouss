package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.Comment;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class CommentRes {

    private RegisterRes author;
    private String content;

    public CommentRes(Comment comment) {
        this.author = new RegisterRes(comment.getAuthor());
        this.content = comment.getContent();
    }

}
