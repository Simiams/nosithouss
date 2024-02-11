package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.CommentReq;
import fr.arosaje.nosithouss.dtos.responses.CommentRes;
import fr.arosaje.nosithouss.models.Comment;
import fr.arosaje.nosithouss.services.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(value = "/create")
    @Operation(summary = "Create a new comment, take the current post and the content")
    public ResponseEntity createComment(@RequestBody CommentReq commentReq) {
        Comment newComment = commentService.createComment(commentReq);
        return ResponseEntity.ok(new CommentRes(newComment));
    }

    @GetMapping(value = "/post/{id}")
    @Operation(summary = "Get all comment by a specific post")
    public ResponseEntity getCommentsByPostId(@PathVariable Long id) {
        List<Comment> comments = commentService.getCommentsByPostId(id);
        List<CommentRes> commentRes = comments.stream().map(CommentRes::new).toList();
        return ResponseEntity.ok(commentRes);
    }
}
