package fr.arosaje.nosithouss.dtos.requests;

import fr.arosaje.nosithouss.models.Message;
import fr.arosaje.nosithouss.models.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MessageReq {

    private String sender;
    private String receiver;
    private String content;

    public Message toMessage() {
        return Message.builder().content(content).build();
    }
}
