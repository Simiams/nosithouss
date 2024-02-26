package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.requests.PostReq;
import fr.arosaje.nosithouss.dtos.requests.ProposalGuardReq;
import fr.arosaje.nosithouss.dtos.requests.SeePostsReq;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.dtos.responses.PostTitleRes;
import fr.arosaje.nosithouss.enums.EPostType;
import fr.arosaje.nosithouss.models.*;
import fr.arosaje.nosithouss.repositories.PostRepository;
import fr.arosaje.nosithouss.repositories.PostRepositoryPersistence;
import fr.arosaje.nosithouss.utils.FileManager;
import fr.arosaje.nosithouss.utils.PostUtils;
import fr.arosaje.nosithouss.utils.Utils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static fr.arosaje.nosithouss.utils.PostUtils.*;
import static fr.arosaje.nosithouss.utils.Utils.now;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostRepositoryPersistence postRepositoryPersistence;
    private final AuthService authService;
    private final FileManager fileManager;

    public PostService(PostRepository postRepository, PostRepositoryPersistence postRepositoryPersistence, AuthService authService, FileManager fileManager) {
        this.postRepository = postRepository;
        this.postRepositoryPersistence = postRepositoryPersistence;
        this.authService = authService;
        this.fileManager = fileManager;
    }

    public PostRes createPost(PostReq postReq) {
        User author = authService.getUser(Utils.getCurrentUserName());
        Post post = createPostByPostReq(postReq);
        post.setAuthor(author);
        post.setCreatedAt(now());
        return createPostResponseByPost(postRepository.save(post));
    }

    public List<Post> getPosts(SeePostsReq seePostsReq) {
        Pageable pageable = PageRequest.of(0, seePostsReq.getNumber());
        return postRepository.findByCreatedAtBeforeOrderByCreatedAtDesc(seePostsReq.getCreatedAt(), pageable).getContent();
    }

    public Post updatePost(PostReq postReq, Long id) {
        User author = authService.getUser(Utils.getCurrentUserName());
        Post newPost = createPostByPostReq(postReq);
        newPost.setAuthor(author);
        newPost.setCreatedAt(now());
        newPost.setId(id);
        return postRepository.save(newPost);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public void upload(MultipartFile file, Long postId) throws IOException {
        Image newImage = fileManager.saveImage(file);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Entity not found with id: " + postId));
        if (post instanceof GuardingPost guardingPost) {
            guardingPost.setImages(List.of(newImage.getName()));
        }
        else if (post instanceof CatalogPost catalogPost) {
            catalogPost.setImages(List.of(newImage.getName()));
        }
        postRepository.save(post);
    }

    public List<PostTitleRes> autocomplete(EPostType postType, String prefix) {
        return postRepositoryPersistence.findByTypeAndTitleStartingWith(getPostTypeByEPostType(postType), prefix)
                .stream().map(p -> new PostTitleRes(p.getTitle(), getFirstImgPostOrNullByPost(p))).toList();
    }

    public void addGuardClaimer(ProposalGuardReq proposalGuardReq) {
        Optional<Post> post = postRepository.findById(proposalGuardReq.getPostId());
        if (post.isPresent() && (Objects.equals(post.get().getAuthor().getUsername(), SecurityContextHolder.getContext().getAuthentication().getName()))) {
            GuardingPost newPost = (GuardingPost) post.get();
            newPost.setGuardClaimer(authService.getUser(proposalGuardReq.getUserName()));
            postRepository.save(newPost);
        }
    }

    public List<PostRes> getOwnPosts() {
        return postRepository.findByAuthor(authService.getUser(Utils.getCurrentUserName())).stream().map(PostUtils::createPostResponseByPost).toList();
    }

    public List<PostRes> getGuardingPosts() {
        return postRepositoryPersistence.findByGuardClaimer(authService.getUser(Utils.getCurrentUserName())).stream().map(PostUtils::createPostResponseByPost).toList();
    }

    public List<PostRes> getAllPostByType(EPostType postType) {
        return postRepositoryPersistence.findByType(getPostTypeByEPostType(postType))
                .stream().map(PostUtils::createPostResponseByPost).toList();
    }

    public List<PostRes> getPostsByProfile(String userName) {
        return postRepository.findByAuthor(authService.getUser(userName)).stream().map(PostUtils::createPostResponseByPost).toList();
    }

    public List<PostRes> getGuardinByUsername(String userName) {
        return postRepositoryPersistence.findByGuardClaimer(authService.getUser(userName)).stream().map(PostUtils::createPostResponseByPost).toList();
    }
}
