package com.springboot.practice.error;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.practice.entity.ErrorMessage;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorMessage> getErrorDetails(DepartmentNotFoundException departmentNotFoundException) {
        return ResponseEntity.status(HttpStatusCode.valueOf(404))
        .body(ErrorMessage.builder()
            .errorMessage(departmentNotFoundException.getMessage())
                .build());
    }
}
