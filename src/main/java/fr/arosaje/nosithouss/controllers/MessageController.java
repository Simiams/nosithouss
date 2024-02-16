package fr.arosaje.nosithouss.controllers;

import fr.arosaje.nosithouss.dtos.requests.MessageGuardAcceptReq;
import fr.arosaje.nosithouss.dtos.requests.MessageGuardReq;
import fr.arosaje.nosithouss.dtos.requests.MessageReq;
import fr.arosaje.nosithouss.dtos.responses.MessageRes;
import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Send a message to the given user (base on his userName and your token)")
    public ResponseEntity createMessage(@RequestBody MessageReq messageReq, @PathVariable String userIdentifier) {
        Message newMessage = messageService.createMessage(messageReq, userIdentifier);
        return ResponseEntity.ok(new MessageRes(newMessage));
    }

    @GetMapping(value = "/{userIdentifier}")
    @Operation(summary = "Get all message you have with the given user")
    public ResponseEntity getMessagesByUser(@PathVariable String userIdentifier) {
        List<Message> messages = messageService.getMessagesByReceiver(userIdentifier);
        return ResponseEntity.ok(messages.stream().map(MessageRes::new).toList());
    }

    @PostMapping(value = "/guard-request/{userIdentifier}")
    @Operation(summary = "Send a guard request to the given user")
    public void createGuardRequest(@RequestBody MessageGuardReq messageGuardReq, @PathVariable String userIdentifier) {
       messageService.createGuardRequest(messageGuardReq, userIdentifier);
    }
    @PostMapping(value = "/guard-request/{messageId}/accept")
    @Operation(summary = "Send a guard request to the given user")
    public void acceptGuardRequest(@RequestBody MessageGuardAcceptReq messageGuardAcceptReq, @PathVariable Long messageId) {
       messageService.acceptGuardRequest(messageGuardAcceptReq, messageId);
    }
}
