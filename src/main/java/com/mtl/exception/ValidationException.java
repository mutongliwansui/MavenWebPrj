package com.mtl.exception;

public class ValidationException extends RuntimeException {
    private Object data;

    public ValidationException(Object data) {
        this.data = data;
    }

    public ValidationException(String message, Object data) {
        super(message);
        this.data = data;
    }

    public ValidationException(String message, Throwable cause, Object data) {
        super(message, cause);
        this.data = data;
    }

    public ValidationException(Throwable cause, Object data) {
        super(cause);
        this.data = data;
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object data) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
