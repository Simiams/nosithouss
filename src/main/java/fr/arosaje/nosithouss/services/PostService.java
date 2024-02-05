package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.requests.PostReq;
import fr.arosaje.nosithouss.dtos.requests.SeePostsReq;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.models.GuardingPost;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.PostRepository;
import fr.arosaje.nosithouss.utils.FileManager;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static fr.arosaje.nosithouss.utils.PostUtils.createPostByPostReq;
import static fr.arosaje.nosithouss.utils.PostUtils.createPostResponseByPost;
import static fr.arosaje.nosithouss.utils.Utils.now;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AuthService authService;

    public PostService(PostRepository postRepository, AuthService authService) {
        this.postRepository = postRepository;
        this.authService = authService;
    }

    public PostRes createPost(PostReq postReq) {
        User author = authService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Post post = createPostByPostReq(postReq);
        post.setAuthor(author);
        post.setCreatedAt(now());
        return createPostResponseByPost(postRepository.save(post));
    }

    public List<Post> getPosts(SeePostsReq seePostsReq) {
        Pageable pageable = PageRequest.of(0, seePostsReq.getNumber());
        return postRepository.findByCreatedAtBeforeOrCreatedAtEqualsOrderByCreatedAtDesc(seePostsReq.getCreatedAt(), seePostsReq.getCreatedAt(), pageable).getContent();
    }

    public Post updatePost(PostReq postReq, Long id) {
        //todo in postReq get Author/creatxedAt
        User author = authService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Post newPost = createPostByPostReq(postReq);
        newPost.setAuthor(author);
        newPost.setCreatedAt(now());
        newPost.setId(id);
        return postRepository.save(newPost);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void upload(MultipartFile file, Long postId) {
        String newFileName = FileManager.saveImage(file);
        //        ApacheClient.uploadImage(file);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + postId));
        if (post instanceof GuardingPost guardingPost) {
            guardingPost.setImg(newFileName);
        }
        else if (post instanceof CatalogPost catalogPost) {
            catalogPost.setImg(newFileName);
        }
        postRepository.save(post);
    }
}
