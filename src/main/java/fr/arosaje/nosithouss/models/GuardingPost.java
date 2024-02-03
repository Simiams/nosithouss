package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;

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
    @Temporal(TemporalType.DATE)
    private Date guardingAt;
    @Temporal(TemporalType.DATE)
    private Date endGuardingAt;
    private String img;
}
