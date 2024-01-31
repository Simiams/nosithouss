package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.requests.PostReq;
import fr.arosaje.nosithouss.dtos.requests.SeePostsReq;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.PostRepository;
import fr.arosaje.nosithouss.utils.FileManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

import static fr.arosaje.nosithouss.utils.PostUtils.createPostByPostReq;
import static fr.arosaje.nosithouss.utils.PostUtils.createPostResponseByPost;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AuthService authService;

    public PostService(PostRepository postRepository, AuthService authService) {
        this.postRepository = postRepository;
        this.authService = authService;
    }

    public PostRes createPost(PostReq postReq, MultipartFile file) {
        FileManager.saveImage(file);
        User author = authService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Post post = createPostByPostReq(postReq);
        post.setAuthor(author);
        post.setCreatedAt( new Date());
        return createPostResponseByPost(postRepository.save(post));
    }

    public List<Post> getPosts(SeePostsReq seePostsReq) {
        Pageable pageable = PageRequest.of(0, seePostsReq.getNumber());
        return postRepository.findByCreatedAtBeforeOrCreatedAtEqualsOrderByCreatedAtDesc(seePostsReq.getCreatedAt(), seePostsReq.getCreatedAt(), pageable).getContent();
    }

    public Post updatePost(PostReq postReq, Long id) {
        //todo in postReq get Author/createdAt
        User author = authService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        Date createdAt = new Date();
        Post newPost = createPostByPostReq(postReq);
        newPost.setAuthor(author);
        newPost.setCreatedAt(createdAt);
        newPost.setId(id);
        return postRepository.save(newPost);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
    }

}
