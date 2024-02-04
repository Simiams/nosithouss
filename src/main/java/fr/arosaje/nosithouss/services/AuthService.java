package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.errors.NosithoussException;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Optional<User> user= userRepository.findByUserName(identifier);
        if (user.isEmpty()) {
            throw new NosithoussException("Username not found");
        }
        return user.get();
    }
}
