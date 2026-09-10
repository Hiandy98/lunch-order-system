package com.lunch.ops.backend.common.exception;

import lombok.Getter;

@Getter
public class DomainException extends RuntimeException{
    private final int statusCode;
    private final Object detail;

    public DomainException(Object detail) {
        super(detail.toString());
        this.statusCode = 500;
        this.detail = detail;
    }

    public DomainException(int statusCode, Object detail) {
        super(detail.toString());
        this.statusCode = statusCode;
        this.detail = detail;
    }
}
