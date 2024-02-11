package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.MessageReq;
import fr.arosaje.nosithouss.dtos.responses.ContactRes;
import fr.arosaje.nosithouss.dtos.responses.MessageRes;
import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.services.ContactService;
import fr.arosaje.nosithouss.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "")
    @Operation(summary = "Get all the user you send a message or who have sent you message, base on your token")
    public ResponseEntity getAllContact() {
        return ResponseEntity.ok(contactService.getAllContact());
    }
}
