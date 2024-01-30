package fr.arosaje.nosithouss.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/test")
public class Test {

//    @GetMapping(value = "/exception")
//    public String exception() {
//        throw new NosithoussException("ErrorMESSAGETEST");
//    }

    @GetMapping(value = "/auth")
    public String auth() {
        return "authentificate";
    }
}
