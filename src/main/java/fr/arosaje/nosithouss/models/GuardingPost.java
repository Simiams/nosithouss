package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.sql.Timestamp;
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
    @Column(name = "coordinatex")
    private float coordinateX;
    @Column(name = "coordinatey")
    private float coordinateY;
    @Column(name = "guarding_at")
    private Timestamp guardingAt;
    @Column(name = "end_guarding_at")
    private Timestamp endGuardingAt;
    private List<String> images;
    @OneToOne
    @JoinColumn(name = "guard_claimer")
    private User guardClaimer;
}
