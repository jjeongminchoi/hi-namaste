package com.jm.hinamaste.global.exception;

import com.jm.hinamaste.global.exception.controller.CustomExceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ClassInfoNotFound extends CustomExceptionHandler {

    private final int STATUS_CODE = HttpStatus.NOT_FOUND.value();
    private static final String MESSAGE = "수업 클래스를 찾을 수 없습니다.";

    public ClassInfoNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }
}
