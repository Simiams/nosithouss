package fr.arosaje.nosithouss.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginReq {

    private String identifierUser;
    private String password;

}