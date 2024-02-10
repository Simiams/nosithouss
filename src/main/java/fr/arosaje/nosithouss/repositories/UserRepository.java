package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserName(String username);

    List<User> findByUserNameStartingWith(String prefix);
}
