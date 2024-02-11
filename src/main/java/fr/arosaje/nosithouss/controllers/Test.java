package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.TrefleReq;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.errors.NosithoussException;
import fr.arosaje.nosithouss.models.CatalogPost;
import fr.arosaje.nosithouss.services.TrefleService;
import fr.arosaje.nosithouss.utils.FileManager;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.tools.JavaCompiler;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/test")
public class Test {
    private final TrefleService trefleService;
    private final FileManager fileManager;
    public Test(TrefleService trefleService, FileManager fileManager) {
        this.trefleService = trefleService;
        this.fileManager = fileManager;
    }

    @GetMapping(value = "/exception")
    @Operation(summary = "[TEST] Test the exceptions")
    public String exception() {
        throw new NosithoussException("test Exception");
    }

    @GetMapping(value = "/{word}")
    @Operation(summary = "[TEST] Test the api with PathVariable")
    public String auth(@PathVariable String word) {
        return word;
    }

    @GetMapping(value = "/hello")
    @Operation(summary = "[TEST] Test say hello")
    public String helloWord() {
        return "Hello World!";
    }

    @GetMapping(value = "/user")
    @Operation(summary = "[TEST] Test get the current user based on his token")
    public String getUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @PostMapping("/upload")
    @Operation(summary = "[TEST] Test upload file")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        fileManager.saveImage(file);
        return "ok";
    }

    @GetMapping("/etl")
    @Operation(summary = "[TEST] Test start the etl")
    public String savePlants() {
        trefleService.savePlants(10);
        return "Ok";
    }
}
