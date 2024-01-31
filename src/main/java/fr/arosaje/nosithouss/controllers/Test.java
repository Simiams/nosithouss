package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.errors.NosithoussException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
}
