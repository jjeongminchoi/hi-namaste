package com.jm.hinamaste.global;

import lombok.Getter;

@Getter
public class ResponseDto<T> {

    private String message;

    private T data;

    public ResponseDto(String message) {
        this.message = message;
    }

    public ResponseDto(final String message, final T data) {
        this.message = message;
        this.data = data;
    }
}
