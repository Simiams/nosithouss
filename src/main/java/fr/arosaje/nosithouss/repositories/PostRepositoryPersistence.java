package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.GuardingPost;
import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryPersistence {

    public List<Post> findByTypeAndTitleStartingWith(Class<?> type, String prefix) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("arosaje");
        EntityManager em = emf.createEntityManager();
        List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE TYPE(p) = :type AND p.title LIKE :prefix", Post.class)
                .setParameter("type", type)
                .setParameter("prefix", "%" + prefix + "%")
                .getResultList();
        em.close();
        emf.close();
        return posts;
    }
    public List<GuardingPost> findByGuardClaimer(User user) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("arosaje");
        EntityManager em = emf.createEntityManager();
        List<GuardingPost> posts = em.createQuery("SELECT p FROM GuardingPost p WHERE p.guardClaimer = :user", GuardingPost.class)
                .setParameter("user", user)
                .getResultList();
        em.close();
        emf.close();
        return posts;
    }

    public List<Post> findByType(Class<?> type) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("arosaje");
        EntityManager em = emf.createEntityManager();
        List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE TYPE(p) = :type", Post.class)
                .setParameter("type", type)
                .getResultList();
        em.close();
        emf.close();
        return posts;
    }
}
