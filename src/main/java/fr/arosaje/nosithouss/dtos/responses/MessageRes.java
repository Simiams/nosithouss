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

    private RegisterRes sender;
    private RegisterRes receiver;
    private String content;
    private Date createdAt;

    public MessageRes(Message message) {
        this.sender = new RegisterRes(message.getSender());
        this.receiver = new RegisterRes(message.getReceiver());
        this.content = message.getContent();
        this.createdAt = message.getCreatedAt();
    }
}
