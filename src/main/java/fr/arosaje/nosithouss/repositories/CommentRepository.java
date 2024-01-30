package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.Comment;
import fr.arosaje.nosithouss.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

}
