package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.requests.CommentReq;
import fr.arosaje.nosithouss.models.Comment;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.repositories.CommentRepository;
import fr.arosaje.nosithouss.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;

import static fr.arosaje.nosithouss.utils.Utils.now;

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
        comment.setAuthor(authService.getUser(Utils.getCurrentUserName()));
        comment.setPost(postService.getPost(commentReq.getPostId()));
        comment.setCreatedAt(now());
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByPostId(Long id) {
        Post post = postService.getPost(id);
        return commentRepository.findAllByPost(post);
    }

}
