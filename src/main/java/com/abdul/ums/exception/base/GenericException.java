package com.abdul.ums.exception.base;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class GenericException extends Exception {

    private String code;
    private List<String> errors;

    public GenericException() {
        super();
    }

    public GenericException(String code, String message) {
        super(message);
        this.code = code;
    }

    public GenericException(String message) {
        super(message);
        this.code = "9999";
    }

    public GenericException(String code, String message, String... errors) {
        super(message);
        this.code = code;
        this.errors = Arrays.asList(errors);
    }



}
