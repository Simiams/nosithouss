package fr.arosaje.nosithouss.repositories;

import fr.arosaje.nosithouss.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmailOrUserName(String email, String userName);

}
