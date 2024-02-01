package fr.arosaje.nosithouss.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TrefleReq {
    private Long id;
    @JsonProperty("common_name")
    private String commonName;
    private String slug;
    @JsonProperty("scientific_name")
    private String scientificName;
    private Integer year;
    private String bibliography;
    private String author;
    private String status;
    private String rank;
    @JsonProperty("family_common_name")
    private String familyCommonName;
    @JsonProperty("genus_id")
    private Long genusId;
    @JsonProperty("image_url")
    private String imageUrl;
    private List<String> synonyms;
    private String genus;
    private String family;
    private Links links;

    @Getter
    @Setter
    public static class Links {
        private String self;
        private String plant;
        private String genus;
    }
}
