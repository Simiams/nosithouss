package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.responses.UserNameRes;
import fr.arosaje.nosithouss.dtos.responses.UserRes;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private UserService userService;

    @GetMapping("/{username}")
    @Operation(summary = "Get profile to a given user")
    public UserRes getUserByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }
    @GetMapping("")
    @Operation(summary = "Get profile of the current uer (based on his token)")
    public UserRes getUser() {
        return userService.getUser();
    }
    @GetMapping("/autocomplete/{usernamePrefix}")
    @Operation(summary = "Autocomplete an username by prefix")
    public List<UserNameRes> getAutocompleteUsername(@PathVariable String usernamePrefix) {
        return userService.getAutocompleteUsername(usernamePrefix);
    }
    @PostMapping("/pdp/{username}")
    @Operation(summary = "Get profile photo of a given user")
    public void saveUserPdp(@RequestParam("file") MultipartFile file, @PathVariable String username) throws IOException {
        userService.saveUserPdp(username, file);
    }
}
