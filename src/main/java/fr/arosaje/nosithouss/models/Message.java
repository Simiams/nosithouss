package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
@Table(name = "messages")
@DiscriminatorValue("message")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
    @Column(columnDefinition = "TEXT")
    private String content;
    @Column(nullable = false, name = "created_at")
    private Timestamp createdAt;

    @Override
    public int compareTo(Message m) {
        return this.getCreatedAt().compareTo(m.getCreatedAt());
    }

}
