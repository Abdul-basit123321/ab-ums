package com.abdul.ums.exception;

import com.abdul.ums.exception.base.GenericException;
import lombok.Data;

@Data
public class NoRoleFoundException extends RuntimeException {

    private String code;
    private String message;

    public NoRoleFoundException(String code,String message) {
        super(code);
        this.code = code;
        this.message = message;
    }
}
