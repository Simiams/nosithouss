package fr.arosaje.nosithouss.models;

import fr.arosaje.nosithouss.enums.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Set<ERole> roles;

    public String[] getRolesStr() {
        return roles.stream().map(ERole::toString).toArray(String[]::new);
    }
}
