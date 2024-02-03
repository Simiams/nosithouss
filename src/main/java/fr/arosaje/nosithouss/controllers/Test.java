package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.TrefleReq;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.errors.NosithoussException;
import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.services.TrefleService;
import fr.arosaje.nosithouss.utils.FileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.tools.JavaCompiler;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/test")
public class Test {
    private final TrefleService trefleService;
    public Test(TrefleService trefleService) {
        this.trefleService = trefleService;
    }

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

    @GetMapping("/etl")
    public String savePlants() {
        trefleService.savePlants();
        return "Ok";
    }
}
