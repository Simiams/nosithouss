package fr.arosaje.nosithouss.models;

import fr.arosaje.nosithouss.dtos.requests.MessageGuardAcceptReq;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "messages_request_guard")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("request_guard")
@SuperBuilder
public class MessageRequestGuard extends Message {
    private Boolean accept;

    @OneToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public MessageRequestGuard bAccept(Boolean accept){
        this.accept = accept;
        return this;
    }
}
