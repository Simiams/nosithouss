package fr.arosaje.nosithouss.repositories;


import fr.arosaje.nosithouss.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByCreatedAtBeforeOrCreatedAtEqualsOrderByCreatedAtDesc(Date date, Date dateEqual, Pageable pageable);

//    @Query("SELECT p FROM Post p WHERE TYPE(p) = :type AND p.title LIKE :prefix%")
//    List<Post> findByTypeAndTitleStartingWith(@Param("type") String type,@Param("prefix") String prefix);
}
