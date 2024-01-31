package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.AuthenticationReq;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.services.JwtService;
import fr.arosaje.nosithouss.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/api/auth")
public class UserController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtService jwtService;

    @PostMapping("/register")
    public void register(@RequestBody User user) {
        log.info("Register for {}", user.getUsername());
        userService.register(user);
    }

    //todo: refacto to dto
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthenticationReq authenticationReq) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.userName(), authenticationReq.password()));

        if (authenticate.isAuthenticated()) {
            return jwtService.generate(authenticationReq.userName());
        }

        log.info("Authentication state {}", authenticate.isAuthenticated());
        return Map.of("yes", "man");
    }
}
