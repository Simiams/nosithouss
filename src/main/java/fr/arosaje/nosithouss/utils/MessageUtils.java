package fr.arosaje.nosithouss.utils;

import fr.arosaje.nosithouss.dtos.responses.CatalogPostRes;
import fr.arosaje.nosithouss.dtos.responses.GuardingPostRes;
import fr.arosaje.nosithouss.dtos.responses.PostRes;
import fr.arosaje.nosithouss.enums.EMessageType;
import fr.arosaje.nosithouss.enums.EPostType;
import fr.arosaje.nosithouss.models.*;

import javax.sound.midi.MetaMessage;
import java.util.List;

public class MessageUtils {

    public static EMessageType getEMessageType(Message message) {
        return switch (message) {
            case MessageRequestGuard messageRequestGuard -> EMessageType.GUARD_CLAIM;
            default -> EMessageType.MESSAGE;
        };
    }
}
