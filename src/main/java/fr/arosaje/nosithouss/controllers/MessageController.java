package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.MessageReq;
import fr.arosaje.nosithouss.dtos.responses.MessageRes;
import fr.arosaje.nosithouss.models.Message;
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

    @PostMapping(value = "/create")
    public ResponseEntity createMessage(@RequestBody MessageReq messageReq) {
        Message newMessage = messageService.createMessage(messageReq);
        return ResponseEntity.ok(new MessageRes(newMessage));
    }

    @GetMapping(value = "/receiver/{id}")
    public ResponseEntity getMessagesByUser(@PathVariable Long id) {
        List<Message> messages = messageService.getMessagesByReceiver(id);
        return ResponseEntity.ok(messages.stream().map(MessageRes::new).toList());
    }

}
