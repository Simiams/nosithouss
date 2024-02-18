package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.PostReq;
import fr.arosaje.nosithouss.dtos.requests.ProposalGuardReq;
import fr.arosaje.nosithouss.dtos.requests.SeePostsReq;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.dtos.responses.PostTitleRes;
import fr.arosaje.nosithouss.enums.EPostType;
import fr.arosaje.nosithouss.errors.ErrorRes;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.services.PostService;
import fr.arosaje.nosithouss.utils.PostUtils;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Operation(summary = "Create a new post, catalog or guarding post (just specify the type)")
    public ResponseEntity createPost(@RequestBody PostReq postReq) {
        List<ErrorRes> errorResponses = new ArrayList<>();
        if (!isValidType(postReq.getType()))
            errorResponses.add(ErrorRes.builder().httpStatus(HttpStatus.BAD_REQUEST).message("Invalid type").build());
        if (!errorResponses.isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponses);

        return ResponseEntity.ok(postService.createPost(postReq));
    }

    @PostMapping(value = "/posts")
    @Operation(summary = "Get x posts prior to a date")
    public ResponseEntity getPosts(@RequestBody SeePostsReq seePostsReq) {
        return ResponseEntity.ok(postService.getPosts(seePostsReq).stream().map(PostUtils::createPostResponseByPost));
    }

    @PutMapping(value = "/{id}")
    @Operation(summary = "Update a post by his id")
    public ResponseEntity updatePostById(@RequestBody PostReq postReq, @PathVariable Long id) {
        Post post = postService.updatePost(postReq, id);
        PostRes postRes = createPostResponseByPost(post);
        return ResponseEntity.ok(postRes);
    }

    @PostMapping("/upload/{postId}")
    @Operation(summary = "Upload relative image to a post...")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String postId) throws IOException {
        Long id = Long.parseLong(postId);
        postService.upload(file, id);
        return ResponseEntity.ok("File Uploaded");
    }

    @GetMapping("/{postType}/autocomplete/{prefix}")
    @Operation(summary = "Autocomplete post by his title")
    public List<PostTitleRes> autocompleteCatalogPost(@PathVariable EPostType postType, @PathVariable String prefix) {
        return postService.autocomplete(postType, prefix);
    }

    @PostMapping("/guard-claimer")
    @Operation(summary = "Add guard claimer to a guarding post")
    public void addGuardClaimer(@RequestBody ProposalGuardReq proposalGuardReq) {
        postService.addGuardClaimer(proposalGuardReq);
    }

    @GetMapping("/profile")
    @Operation(summary = "Get all post by token user")
    public List<PostRes> getOwnPosts() {
        return postService.getOwnPosts();
    }

    @GetMapping("/guarding")
    @Operation(summary = "Get all post that user guard by token user")
    public List<PostRes> getGuardingPosts() {
        return postService.getGuardingPosts();
    }

    @GetMapping("/{postType}")
    @Operation(summary = "Get all post by type")
    public List<PostRes> getAllPostByType(@PathVariable EPostType postType){
        var test = postService.getAllPostByType(postType);
        return test;
    }
}
