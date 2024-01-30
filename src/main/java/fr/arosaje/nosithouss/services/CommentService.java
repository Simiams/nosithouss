package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.requests.CommentReq;
import fr.arosaje.nosithouss.models.Comment;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.repositories.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final AuthService authService;
    private final PostService postService;

    public CommentService(CommentRepository commentRepository, AuthService authService, PostService postService) {
        this.commentRepository = commentRepository;
        this.authService = authService;
        this.postService = postService;
    }

    public Comment createComment(CommentReq commentReq) {
        Comment comment = commentReq.toComment();
        comment.setAuthor(authService.getUser(commentReq.getIdentifierAuthor()));
        comment.setPost(postService.getPost(commentReq.getPostId()));
        comment.setCreatedAt(new Date());
        Comment newComment = commentRepository.save(comment);
        return newComment;
    }

    public List<Comment> getCommentsByPostId(Long id) {
        Post post = postService.getPost(id);
        return commentRepository.findAllByPost(post);
    }

}
