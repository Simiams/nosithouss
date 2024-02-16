package fr.arosaje.nosithouss.repositories;


import fr.arosaje.nosithouss.models.Post;
import fr.arosaje.nosithouss.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByCreatedAtBeforeOrderByCreatedAtDesc(Timestamp date, Pageable pageable);
    List<Post> findByAuthor(User author);


}
