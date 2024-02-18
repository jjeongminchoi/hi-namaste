package com.jm.hinamaste.global.exception;

import com.jm.hinamaste.global.exception.controller.CustomExceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberTicketNotFound extends CustomExceptionHandler {

    private final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    private static final String MESSAGE = "보유한 수강권이 없습니다.";

    public MemberTicketNotFound() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }
}
