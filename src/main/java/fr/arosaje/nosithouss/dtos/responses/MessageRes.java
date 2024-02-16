package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.enums.EMessageType;
import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.models.MessageRequestGuard;
import fr.arosaje.nosithouss.utils.MessageUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MessageRes {

    private EMessageType type;
    private String senderIdentifier;
    private String content;
    private Date createdAt;
    private Boolean accept;
    private Long postId;
    private Long id;

    public MessageRes(Message message) {
        this.id = message.getId();
        this.senderIdentifier = message.getSender().getUsername();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
        this.type = MessageUtils.getEMessageType(message);
        this.accept = (message instanceof MessageRequestGuard messagerequestguard) ? messagerequestguard.getAccept() : null;
        this.postId = (message instanceof MessageRequestGuard messagerequestguard) ? messagerequestguard.getPost().getId() : null;
    }
}
