package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class MessageRes {

    private String senderIdentifier;
    private String content;
    private Date createdAt;

    public MessageRes(Message message) {
        this.senderIdentifier = message.getSender().getUsername();
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
    }
}
