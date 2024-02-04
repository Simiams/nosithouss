package fr.arosaje.nosithouss.dtos.requests;

import fr.arosaje.nosithouss.enums.ERole;
import fr.arosaje.nosithouss.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
public class RegisterReq {
    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Set<String> roles;

    public User toUser() {
        return User.builder().userName(userName).password(password).firstName(firstName).lastName(lastName).roles(roles.stream().map(ERole::fromString).collect(Collectors.toSet())).build();
    }

}
