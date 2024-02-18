package com.jm.hinamaste.global.exception;

import com.jm.hinamaste.global.exception.controller.CustomExceptionHandler;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MonthlyUsageExhausted extends CustomExceptionHandler {

    private final int STATUS_CODE = HttpStatus.BAD_REQUEST.value();
    private static final String MESSAGE = "월간 이용 횟수를 모두 사용하였습니다.";

    public MonthlyUsageExhausted() {
        super(MESSAGE);
    }

    @Override
    public int getStatusCode() {
        return STATUS_CODE;
    }
}
