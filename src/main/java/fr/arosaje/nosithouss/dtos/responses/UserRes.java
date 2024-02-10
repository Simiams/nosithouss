package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.enums.ERole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRes {
    private String userName;
    private String firstName;
    private String lastName;
    private Set<ERole> roles;
    private String pdp;
}
