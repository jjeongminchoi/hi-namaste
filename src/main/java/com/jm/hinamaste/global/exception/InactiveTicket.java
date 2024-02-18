package com.jm.hinamaste.global.exception;

import com.jm.hinamaste.global.exception.controller.CustomExceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class InactiveTicket extends CustomExceptionHandler {

    private final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    private static final String MESSAGE = "수강권이 비활성 상태입니다.";

    public InactiveTicket() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }
}
