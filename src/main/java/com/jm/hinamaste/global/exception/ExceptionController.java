package com.jm.hinamaste.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApiResponse> invalidRequestHandler(MethodArgumentNotValidException e) {
        ErrorApiResponse response = ErrorApiResponse.builder()
                .code(BAD_REQUEST.name())
                .message("잘못된 요청입니다.")
                .build();

        for (FieldError error : e.getFieldErrors()) {
            response.addValidation(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.status(BAD_REQUEST).body(response);
    }
}
