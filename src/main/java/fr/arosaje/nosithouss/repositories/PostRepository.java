package fr.arosaje.nosithouss.repositories;


import fr.arosaje.nosithouss.models.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findByCreatedAtBeforeOrCreatedAtEqualsOrderByCreatedAtDesc(Date date, Date dateEqual, Pageable pageable);

}
