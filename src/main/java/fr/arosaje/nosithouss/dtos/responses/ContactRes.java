package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.models.Contact;
import fr.arosaje.nosithouss.models.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactRes {
    private String userName;
    private Date lastChat;
}
