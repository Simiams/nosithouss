package fr.arosaje.nosithouss.services;

import fr.arosaje.nosithouss.dtos.requests.MessageGuardAcceptReq;
import fr.arosaje.nosithouss.dtos.requests.MessageGuardReq;
import fr.arosaje.nosithouss.dtos.requests.MessageReq;
import fr.arosaje.nosithouss.dtos.requests.ProposalGuardReq;
import fr.arosaje.nosithouss.models.Contact;
import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.models.MessageRequestGuard;
import fr.arosaje.nosithouss.models.User;
import fr.arosaje.nosithouss.repositories.MessageGuardRepository;
import fr.arosaje.nosithouss.repositories.MessageRepository;
import fr.arosaje.nosithouss.utils.Utils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static fr.arosaje.nosithouss.utils.Utils.now;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final MessageGuardRepository messageGuardRepository;
    private final ContactService contactService;
    private final PostService postService;
    private final AuthService authService;

    public MessageService(MessageRepository messageRepository, MessageGuardRepository messageGuardRepository, ContactService contactService, PostService postService, AuthService authService) {
        this.messageRepository = messageRepository;
        this.messageGuardRepository = messageGuardRepository;
        this.contactService = contactService;
        this.postService = postService;
        this.authService = authService;
    }

    public Message createMessage(MessageReq messageReq, String userIdentifier) {
        Message message = messageReq.toMessage();
        message.setCreatedAt(now());
        message.setSender(authService.getUser(Utils.getCurrentUserName()));
        message.setReceiver(authService.getUser(userIdentifier));
        contactService.safeSaveContact(Contact.builder()
                                               .user(message.getSender())
                                               .lastChat(now())
                                               .contactUser(message.getReceiver())
                                               .build());
        return messageRepository.save(message);
    }

    public List<Message> getMessagesByReceiver(String userIdentifier) {
        User currentUser = authService.getUser(Utils.getCurrentUserName());
        return Stream.concat(
                        messageRepository.findAllByReceiverAndSender(currentUser, authService.getUser(userIdentifier)).stream(),
                        messageRepository.findAllByReceiverAndSender(authService.getUser(userIdentifier), currentUser).stream())
                .sorted().toList();
    }

    public void createGuardRequest(MessageGuardReq messageGuardReq, String userIdentifier) {
        messageGuardRepository.save(MessageRequestGuard.builder()
                                            .post(postService.getPost(messageGuardReq.getPostId()))
                                            .createdAt(now())
                                            .content(messageGuardReq.getContent())
                                            .receiver(authService.getUser(userIdentifier))
                                            .sender(authService.getUser(Utils.getCurrentUserName()))
                                            .build());
    }

    public void acceptGuardRequest(MessageGuardAcceptReq messageGuardAcceptReq, Long messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent())
            if (message.get() instanceof MessageRequestGuard messagerequestguard) {
                messageRepository.save(messagerequestguard.bAccept(messageGuardAcceptReq.getAccept()));
                if (messageGuardAcceptReq.getAccept())
                    postService.addGuardClaimer(ProposalGuardReq.builder().postId(messagerequestguard.getPost().getId()).userName(messagerequestguard.getSender().getUsername()).build());
            }
    }
}
