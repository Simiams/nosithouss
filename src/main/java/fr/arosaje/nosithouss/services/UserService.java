package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.enums.ERole;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public void register(User user) {
        //todo validator
        Optional<User> optionalUser = this.userRepository.findByUserName(user.getUsername());
        if (optionalUser.isPresent()) {
            throw new RuntimeException("Votre username est déjà utilisé");
        }
        String newPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(newPassword);
        user.setRoles(Set.of(ERole.USER));
        userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUserName(username).orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}