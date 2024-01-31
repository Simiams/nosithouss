package fr.arosaje.nosithouss.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Controller
public class NosithoussExceptionHandler {

    @ExceptionHandler(value = NosithoussException.class)
    public ResponseEntity<ErrorRes> handleNosithoussException() {
        ErrorRes errorRes = new ErrorRes(HttpStatus.NO_CONTENT, "desc", new Date());
        return new ResponseEntity<ErrorRes>(errorRes, HttpStatus.BAD_REQUEST);
    }
}
