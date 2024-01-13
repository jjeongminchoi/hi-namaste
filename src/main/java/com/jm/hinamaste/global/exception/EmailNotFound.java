package com.jm.hinamaste.global.exception;

import com.jm.hinamaste.global.exception.controller.CustomExceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class EmailNotFound extends CustomExceptionHandler {

    private final int STATUS_CODE = HttpStatus.NOT_FOUND.value();
    private static final String MESSAGE = "이메일을 찾을 수 없습니다.";

    public EmailNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }
}
