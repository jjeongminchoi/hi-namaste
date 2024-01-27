package com.jm.hinamaste.global.exception;

import com.jm.hinamaste.global.exception.controller.CustomExceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InstructorNotFound extends CustomExceptionHandler {

    private final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    private static final String MESSAGE = "강사가 아닙니다.";

    public InstructorNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }
}
