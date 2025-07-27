package com.abdul.ums.exception;

import com.abdul.ums.exception.base.GenericException;

public class NoRoleFoundException extends RuntimeException {

    private String code;
    private String message;

    public NoRoleFoundException(String code,String message) {
        super(code);
        this.code = code;
        this.message = message;
    }
}
