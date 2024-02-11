package fr.arosaje.nosithouss.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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

    private String additionalProperties;
    private List<String> images;
}
