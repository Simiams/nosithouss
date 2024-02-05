package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.responses.UserNameRes;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private UserService userService;

    @GetMapping("/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
    @GetMapping("/autocomplete/{usernamePrefix}")
    public List<UserNameRes> getAutocompleteUsername(@PathVariable String usernamePrefix) {
        return userService.getAutocompleteUsername(usernamePrefix);
    }
}
