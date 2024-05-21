package com.kk119.cq_takeout.exception;

public class BaseException extends RuntimeException {
    public BaseException() {}
    public BaseException(String message) {
        super(message);
    }
}
