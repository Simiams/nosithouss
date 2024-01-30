package fr.arosaje.nosithouss.validators;

import fr.arosaje.nosithouss.enums.EPostType;

public class PostValidator {
    public static boolean   isValidType(String type) {
        return type != null
                && EPostType.fromString(type) != null;
    }
}
