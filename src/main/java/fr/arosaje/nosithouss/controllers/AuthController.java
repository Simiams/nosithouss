package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.AuthenticationReq;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.services.JwtService;
import fr.arosaje.nosithouss.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private JwtService jwtService;


    @PostMapping("/register")
    public void register(@RequestBody User user) {
        log.info("[NOSITHOUS] [auth] Post api/auth/register");
        userService.register(user);
    }

    //todo: refacto to dto
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody AuthenticationReq authenticationReq) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.userName(), authenticationReq.password()));

        if (authenticate.isAuthenticated()) {
            return jwtService.generate(authenticationReq.userName());
        }
        return Map.of("yes", "man");
    }
}
