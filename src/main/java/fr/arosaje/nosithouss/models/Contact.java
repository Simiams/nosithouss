package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne
    @JoinColumn(name = "contact_id")
    private User contactUser;

    @Column(name = "last_chat")
    private Timestamp lastChat;

    public Contact bSetLastChat(Timestamp date) {
        this.lastChat = date;
        return this;
    }
}
