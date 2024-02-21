package com.jm.hinamaste.global.exception;

import com.jm.hinamaste.global.exception.controller.CustomExceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InvalidMember extends CustomExceptionHandler {

    private final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    private static final String MESSAGE = "유효하지 않은 회원입니다.";

    public InvalidMember() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }
}
