package com.jm.hinamaste.domain.member.dto;

import lombok.Getter;

@Getter
public class ResponseDto<T> {

    private T data;

    public ResponseDto(T data) {
        this.data = data;
    }
}
