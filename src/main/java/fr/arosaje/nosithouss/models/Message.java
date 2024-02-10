package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message implements Comparable<Message> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    private String content;
    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    @Override
    public int compareTo(Message m) {
        return this.getCreatedAt().compareTo(m.getCreatedAt());
    }

}
