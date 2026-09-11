package com.lunch.ops.backend.common.exception;

public class InvalidBusinessLogicException extends DomainException{
    public InvalidBusinessLogicException(Object detail) {
        super(400, detail);
    }
}
