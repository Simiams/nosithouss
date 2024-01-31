package fr.arosaje.nosithouss.utils;

import fr.arosaje.nosithouss.dtos.responses.CatalogPostRes;
import fr.arosaje.nosithouss.dtos.responses.GuardingPostRes;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.enums.EPostType;
import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.models.GuardingPost;
import fr.arosaje.nosithouss.models.Post;

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

    public static <T extends Post> T getPostInstance(T post) {
        if (post instanceof CatalogPost)
            return (T) new CatalogPost();
        else if (post instanceof GuardingPost)
            return (T) new GuardingPost();
        else
            return post;
    }
}
