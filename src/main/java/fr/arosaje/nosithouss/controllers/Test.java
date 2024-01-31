package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.errors.NosithoussException;
import fr.arosaje.nosithouss.utils.FileManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/test")
public class Test {

    @GetMapping(value = "/exception")
    public String exception() {
        throw new NosithoussException();
    }

    @GetMapping(value = "/{word}")
    public String auth(@PathVariable String word) {
        return word;
    }

    @GetMapping(value = "/hello")
    public String helloWord() {
        return "Hello World!";
    }

    @GetMapping(value = "/user")
    public String getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        FileManager.saveImage(file);
        return "word";
    }
}
