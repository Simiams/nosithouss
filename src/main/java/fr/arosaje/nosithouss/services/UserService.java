package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.responses.UserNameRes;
import fr.arosaje.nosithouss.enums.ERole;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.UserRepository;
import fr.arosaje.nosithouss.utils.FileManager;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

    public List<UserNameRes> getAutocompleteUsername(String usernamePrefix) {
        return userRepository.findByUserNameStartingWith(usernamePrefix).stream().map(user -> new UserNameRes(user.getUsername())).toList();
    }

    public void saveUserPdp(String username, MultipartFile file) {
        String imageUUID = FileManager.saveImage(file);
        Optional<User> user = userRepository.findByUserName(username);
        user.ifPresent(value -> userRepository.save(value.bImage(imageUUID)));
    }
}