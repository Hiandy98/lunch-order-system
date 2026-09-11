package com.lunch.ops.backend.common.exception;

public class UnauthorizedError extends DomainException{
    public UnauthorizedError(Object detail) {
        super(401, detail);
    }
}
