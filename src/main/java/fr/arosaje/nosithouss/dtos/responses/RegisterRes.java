package fr.arosaje.nosithouss.dtos.responses;

import fr.arosaje.nosithouss.enums.ERole;
import fr.arosaje.nosithouss.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRes {
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private Set<String> roles;

    public RegisterRes(User user) {
        this.email = user.getEmail();
        this.userName = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.roles = user.getRoles().stream().map(ERole::toString).collect(java.util.stream.Collectors.toSet());
    }
}
