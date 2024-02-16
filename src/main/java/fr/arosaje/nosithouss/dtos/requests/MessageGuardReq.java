package fr.arosaje.nosithouss.dtos.requests;

import fr.arosaje.nosithouss.enums.EMessageType;
import fr.arosaje.nosithouss.models.Message;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageGuardReq extends MessageReq {
    private EMessageType type;
    private Boolean accept;
    private Long postId;
}
