package com.example.internalAdminDashboard.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Responsible to handle exceptions
public class LoanExceptionController {
    @ExceptionHandler(value = {LoanNotFoundException.class})
    public ResponseEntity<Object> handleLoanNotFoundException(LoanNotFoundException loanNotFoundException) {
        LoanException loanException = new LoanException(
                loanNotFoundException.getMessage(),
                loanNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
                return new ResponseEntity<>(loanException, HttpStatus.NOT_FOUND);
    }
}
