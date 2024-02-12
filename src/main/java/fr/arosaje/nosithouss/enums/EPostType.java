package fr.arosaje.nosithouss.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum EPostType {
    CATALOG,
    GUARDING,
    POST;
    public static EPostType fromString(String type) {
        return Arrays.stream(EPostType.values()).filter(r -> r.toString().equals(type)).findFirst().orElse(null);
    }
}
