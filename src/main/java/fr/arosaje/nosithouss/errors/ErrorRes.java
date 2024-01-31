package fr.arosaje.nosithouss.errors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ErrorRes {

    HttpStatus httpStatus;
    String message;
    Date date;


    public ErrorRes(HttpStatus httpStatus, String invalidUsernameOrPassword) {
        this.httpStatus = httpStatus;
        this.message = invalidUsernameOrPassword;
    }

}