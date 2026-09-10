package com.lunch.ops.backend.common.exception;

public class ConflictError extends DomainException {
    public ConflictError(Object detail) {
        super(409, detail);
    }
}
