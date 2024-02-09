package com.example.ExceptionHandler;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetail> exceptionHandler(ResourceNotFoundException e, WebRequest webRequest)

    {
        ErrorDetail ed = new ErrorDetail(e.getMessage(),new Date(),webRequest.getDescription(true));
        return new ResponseEntity<>(ed, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
