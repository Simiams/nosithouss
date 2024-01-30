package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailOrUserName(String email, String userName);

    Optional<User> findByUserName(String username);

}
