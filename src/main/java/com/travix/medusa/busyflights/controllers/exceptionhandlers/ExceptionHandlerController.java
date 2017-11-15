package com.travix.medusa.busyflights.controllers.exceptionhandlers;

import com.travix.medusa.busyflights.domain.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    /**
     * method to indicate to the user that his request has triggered an error
     * @param ex Exception that has been thrown
     * @return a ResponseEntity consisting of ErrorCode 400 and a generic error message
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ex.printStackTrace();
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setMessage("The entered request has caused an error. Please contact your administrator");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}