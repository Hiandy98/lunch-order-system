package com.lunch.ops.backend.common.exception;

public class NotFoundError extends DomainException {
    public NotFoundError(Object detail) {
        super(404, detail);
    }
}
