package com.github.achuzhmarov.tutorial.exception;

public abstract class ApplicationException extends RuntimeException {
    ApplicationException(String message) {
        super(message);
    }

    ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }
}
