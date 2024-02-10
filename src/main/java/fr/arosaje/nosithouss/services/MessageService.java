package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.requests.MessageReq;
import fr.arosaje.nosithouss.models.Contact;
import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.ContactRepository;
import fr.arosaje.nosithouss.repositories.MessageRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import static fr.arosaje.nosithouss.utils.Utils.now;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ContactService contactService;
    private final AuthService authService;

    public MessageService(MessageRepository messageRepository, ContactService contactService, AuthService authService) {
        this.messageRepository = messageRepository;
        this.contactService = contactService;
        this.authService = authService;
    }

    public Message createMessage(MessageReq messageReq, String userIdentifier) {
        Message message = messageReq.toMessage();
        message.setCreatedAt(now());
        message.setSender(authService.getUser(SecurityContextHolder.getContext().getAuthentication().getName())); //todo global method
        message.setReceiver(authService.getUser(userIdentifier));
        contactService.safeSaveContact(Contact.builder()
                                       .user(message.getSender())
                                       .lastChat(now())
                                       .contactUser(message.getReceiver())
                                       .build());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByReceiver(String userIdentifier) {
        User currentUser =authService.getUser(SecurityContextHolder.getContext().getAuthentication().getName());
        return Stream.concat(
                        messageRepository.findAllByReceiverAndSender(currentUser, authService.getUser(userIdentifier)).stream(),
                        messageRepository.findAllByReceiverAndSender(authService.getUser(userIdentifier), currentUser).stream())
                .sorted().toList();
    }
}
