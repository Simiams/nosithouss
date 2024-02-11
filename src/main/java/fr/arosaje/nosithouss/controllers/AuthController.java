package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.AuthenticationReq;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.services.JwtService;
import fr.arosaje.nosithouss.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.RouterOperation;
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
    @Operation(summary = "Register a new account")
    public void register(@RequestBody User user) {
        log.info("[NOSITHOUS] [auth] Post api/auth/register");
        userService.register(user);
    }

    //todo: refacto to dto
    @PostMapping("/login")
    @Operation(summary = "Login to your account, send security jwt token if login successfully")
    public Map<String, String> login(@RequestBody AuthenticationReq authenticationReq) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationReq.userName(), authenticationReq.password()));

        if (authenticate.isAuthenticated()) {
            return jwtService.generate(authenticationReq.userName());
        }
        return Map.of("yes", "man");
    }
}
