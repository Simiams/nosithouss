package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.Image;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findByName(String imageUUID);
}
