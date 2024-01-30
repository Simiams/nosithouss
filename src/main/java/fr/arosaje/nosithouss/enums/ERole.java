package fr.arosaje.nosithouss.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ERole {
    ADMIN,
    USER,
    BOTANISTE;

    public static ERole fromString(String role) {
        return Arrays.stream(ERole.values()).filter(r -> r.toString().equals(role)).findFirst().orElse(null);
    }
}
