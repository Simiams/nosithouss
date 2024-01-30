package fr.arosaje.nosithouss.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class LoginRes {

    private String name;
    private String token;
    private List<String> roles;

}