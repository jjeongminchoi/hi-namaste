package com.jm.hinamaste.global.exception.controller;

import lombok.Getter;

@Getter
public abstract class CustomExceptionHandler extends RuntimeException {

    public abstract int getStatusCode();

    public CustomExceptionHandler(String message) {
        super(message);
    }
}
