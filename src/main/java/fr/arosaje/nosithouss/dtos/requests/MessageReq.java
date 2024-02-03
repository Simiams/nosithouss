package fr.arosaje.nosithouss.dtos.requests;

import fr.arosaje.nosithouss.models.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageReq {

    private String content;
    public Message toMessage() {
        return Message.builder().content(content).build();
    }
}
