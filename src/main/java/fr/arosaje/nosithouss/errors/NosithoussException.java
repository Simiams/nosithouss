package fr.arosaje.nosithouss.errors;

import lombok.Getter;
@Getter
public class NosithoussException extends RuntimeException {

    public NosithoussException(String message) {
        super(message); // Remplacez par le code d'état que vous souhaitez
    }

}
