package com.jm.hinamaste.global.exception;

import com.jm.hinamaste.global.exception.controller.CustomExceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CourseNotFound extends CustomExceptionHandler {

    private final int STATUS_CODE = HttpStatus.NOT_FOUND.value();
    private static final String MESSAGE = "수업을 찾을 수 없습니다.";

    public CourseNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }
}
