package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "images")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Image {
    @Id
    private String name;
    @Lob
    private byte[] data;
}
