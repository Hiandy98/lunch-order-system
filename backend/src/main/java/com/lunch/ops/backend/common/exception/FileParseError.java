package com.lunch.ops.backend.common.exception;

public class FileParseError extends DomainException{
    public FileParseError(Object detail) {
        super(400, detail);
    }
}
