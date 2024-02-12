package fr.arosaje.nosithouss.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@Entity
@Table(name = "plant_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("catalog")
@SuperBuilder
public class CatalogPost extends Post {
    @Column(name = "additional_properties")
    private String additionalProperties;
    private List<String> images;
}
