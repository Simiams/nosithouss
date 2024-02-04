package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "flags")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Flag {
    @Id
    private String key;
    private String value;
    private Timestamp date;
}


