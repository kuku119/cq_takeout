package com.kk119.cq_takeout.exception;

public class UserNotLoginException extends BaseException{
    public UserNotLoginException() {}
    public UserNotLoginException(String message) {
        super(message);
    }
}
