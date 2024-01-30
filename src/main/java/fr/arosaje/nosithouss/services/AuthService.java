package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(String identifier) {
        return userRepository.findByEmailOrUserName(identifier, identifier);
    }
}
