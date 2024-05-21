package com.kk119.cq_takeout.exception;

public class AccountNotFoundException extends BaseException{
    public AccountNotFoundException() {}
    public AccountNotFoundException(String message) {
        super(message);
    }
}
