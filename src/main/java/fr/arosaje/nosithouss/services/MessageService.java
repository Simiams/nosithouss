package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.requests.MessageReq;
import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.repositories.MessageRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AuthService authService;

    public MessageService(MessageRepository messageRepository, AuthService authService) {
        this.messageRepository = messageRepository;
        this.authService = authService;
    }

    public Message createMessage(MessageReq messageReq) {
        Message message = messageReq.toMessage();
        message.setCreatedAt(new Date());
        message.setSender(authService.getUser(SecurityContextHolder.getContext().getAuthentication().getName())); //todo global method
        message.setReceiver(authService.getUser(messageReq.getReceiver()));
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByReceiver(Long id) {
        return messageRepository.findAllByReceiverId(id);
    }

}
