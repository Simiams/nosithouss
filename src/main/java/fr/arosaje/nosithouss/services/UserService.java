package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.responses.UserNameRes;
import fr.arosaje.nosithouss.dtos.responses.UserRes;
import fr.arosaje.nosithouss.enums.ERole;
import fr.arosaje.nosithouss.errors.NosithoussException;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.UserRepository;
import fr.arosaje.nosithouss.utils.FileManager;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final FileManager fileManager;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, FileManager fileManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.fileManager = fileManager;
    }

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

    public UserRes findByUsername(String username) {
        Optional<User> user = userRepository.findByUserName(username);
        if (user.isPresent()) {
            User currentUser = user.get();
            return UserRes.builder()
                    .userName(currentUser.getUsername())
                    .roles(currentUser.getRoles())
                    .pdp(currentUser.getPdp())
                    .lastName(currentUser.getLastName())
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }

    public List<UserNameRes> getAutocompleteUsername(String usernamePrefix) {
        return userRepository.findByUserNameStartingWith(usernamePrefix).stream().map(user -> new UserNameRes(user.getUsername(), user.getPdp())).toList();
    }

    public void saveUserPdp(String username, MultipartFile file) throws IOException {
        String imageUUID = fileManager.saveImage(file).getName();
        Optional<User> user = userRepository.findByUserName(username);
        user.ifPresent(value -> userRepository.save(value.bPdp(imageUUID)));
    }

    public UserRes getUser() {
        Optional<User> user = userRepository.findByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        if (user.isPresent()) {
            User currentUser = user.get();
            return UserRes.builder()
                    .userName(currentUser.getUsername())
                    .roles(currentUser.getRoles())
                    .pdp(currentUser.getPdp())
                    .lastName(currentUser.getLastName())
                    .build();
        }
        throw new UsernameNotFoundException("User not found");
    }
}