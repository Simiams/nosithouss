package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.MessageReq;
import fr.arosaje.nosithouss.dtos.responses.MessageRes;
import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.services.MessageService;
import org.instancio.Instancio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    private final MessageService messageService;


    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "/{userIdentifier}")
    public ResponseEntity createMessage(@RequestBody MessageReq messageReq, @PathVariable String userIdentifier) {
        Message newMessage = messageService.createMessage(messageReq, userIdentifier);
        return ResponseEntity.ok(new MessageRes(newMessage));
    }

    @GetMapping(value = "/{userIdentifier}")
    public ResponseEntity getMessagesByUser(@PathVariable String userIdentifier) {
        List<Message> messages = messageService.getMessagesByReceiver(userIdentifier);
        return ResponseEntity.ok(messages.stream().map(MessageRes::new).toList());
    }
}
