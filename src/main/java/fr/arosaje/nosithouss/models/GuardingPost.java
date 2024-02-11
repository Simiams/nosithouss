package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plant_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("guarding")
@SuperBuilder
public class GuardingPost extends Post {

    private int coordinateX;
    private int coordinateY;
    private Timestamp guardingAt;
    private Timestamp endGuardingAt;
    private List<String> images;
}
