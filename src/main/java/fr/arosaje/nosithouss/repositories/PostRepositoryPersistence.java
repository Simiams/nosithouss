package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryPersistence {

    public List<Post> findByTypeAndTitleStartingWith(Class<?> type, String prefix) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("arosaje"); //todo add .env var fr prod...
        EntityManager em = emf.createEntityManager();
        List<Post> posts = em.createQuery("SELECT p FROM Post p WHERE TYPE(p) = :type AND p.title LIKE :prefix", Post.class)
                .setParameter("type", type)
                .setParameter("prefix", "%" + prefix + "%")
                .getResultList();
        em.close();
        emf.close();
        return posts;
    }
}
