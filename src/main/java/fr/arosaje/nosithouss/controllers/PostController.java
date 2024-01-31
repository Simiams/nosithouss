package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.PostReq;
import fr.arosaje.nosithouss.dtos.requests.SeePostsReq;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.errors.ErrorRes;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.services.PostService;
import fr.arosaje.nosithouss.utils.PostUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static fr.arosaje.nosithouss.utils.PostUtils.createPostResponseByPost;
import static fr.arosaje.nosithouss.validators.PostValidator.isValidType;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity createPost(@RequestBody PostReq postReq, @RequestPart("file") MultipartFile file) {
        List<ErrorRes> errorResponses = new ArrayList<>();
        if (!isValidType(postReq.getType()))
            errorResponses.add(ErrorRes.builder().httpStatus(HttpStatus.BAD_REQUEST).message("Invalid type").build());
        if (!errorResponses.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponses);

        return ResponseEntity.ok(postService.createPost(postReq, file));
    }

    @PostMapping(value = "/posts")
    public ResponseEntity getPosts(@RequestBody SeePostsReq seePostsReq) {
        return ResponseEntity.ok(postService.getPosts(seePostsReq).stream().map(PostUtils::createPostResponseByPost));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updatePostById(@RequestBody PostReq postReq, @PathVariable Long id) {
        Post post = postService.updatePost(postReq, id);
        PostRes postRes = createPostResponseByPost(post);
        return ResponseEntity.ok(postRes);
    }
}
