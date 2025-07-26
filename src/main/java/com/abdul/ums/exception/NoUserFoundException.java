package com.abdul.ums.exception;

import com.abdul.ums.exception.base.GenericException;

public class NoUserFoundException extends GenericException {
    private String code;
    private String message;

    public NoUserFoundException(String code,String message) {
        super(code,message);
        this.code = code;
        this.message = message;
    }

}
