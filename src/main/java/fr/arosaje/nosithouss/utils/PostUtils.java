package fr.arosaje.nosithouss.utils;

import fr.arosaje.nosithouss.dtos.responses.CatalogPostRes;
import fr.arosaje.nosithouss.dtos.responses.GuardingPostRes;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.enums.EPostType;
import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.models.GuardingPost;
import fr.arosaje.nosithouss.models.Post;

import java.util.List;

public class PostUtils {

    public static PostRes createPostResponseByPost(Post post) {
        return switch (post) {
            case CatalogPost catalogPost -> new CatalogPostRes(catalogPost);
            case GuardingPost guardingPost -> new GuardingPostRes(guardingPost);
            default -> new PostRes(post);
        };
    }

    public static Post createPostByPostReq(fr.arosaje.nosithouss.dtos.requests.PostReq postReq) {
        return switch (EPostType.fromString(postReq.getType())) {
            case CATALOG -> postReq.toCatalogPost();
            case GUARDING -> postReq.toGuardingPost();
            default -> postReq.toPost();
        };
    }

    public static Class<?> getPostTypeByEPostType(EPostType ePostType) {
        return switch (ePostType) {
            case CATALOG -> CatalogPost.class;
            case GUARDING -> GuardingPost.class;
            default -> Post.class;
        };
    }

    public static EPostType getEPostByPost(Post post) {
        return switch (post) {
            case CatalogPost catalogPost -> EPostType.CATALOG;
            case GuardingPost guardingPost -> EPostType.GUARDING;
            default -> EPostType.POST;
        };
    }

    public static <T extends Post> T getPostInstance(T post) {
        if (post instanceof CatalogPost)
            return (T) new CatalogPost();
        else if (post instanceof GuardingPost)
            return (T) new GuardingPost();
        else
            return post;
    }

    public static String getFirstImgPostOrNullByPost(Post post) {
        return switch (getEPostByPost(post)) {
            case CATALOG -> ((CatalogPost) post).getImages().getFirst();
            case GUARDING -> ((GuardingPost) post).getImages().getFirst();
            default -> null;
        };
    }

    public static List<String> getAllImagesByPost(Post post) {
        return switch (getEPostByPost(post)) {
            case CATALOG -> ((CatalogPost) post).getImages();
            case GUARDING -> ((GuardingPost) post).getImages();
            default -> null;
        };
    }
}
