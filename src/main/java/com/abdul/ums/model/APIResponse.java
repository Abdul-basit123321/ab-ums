package com.abdul.ums.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

import static com.abdul.ums.constant.AppConstant.OK_CODE;
import static com.abdul.ums.constant.AppConstant.OK_MESSAGE;

@Data
public class APIResponse<T> implements Serializable {

    private String code;
    private String message;
    private T data;
    private List<String> errors;


    public static <T> APIResponse<T> ok(T data) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(OK_CODE);
        response.setMessage(OK_MESSAGE);
        response.setData(data);
        return response;
    }

    public static <T> APIResponse<T> error(String code, String message) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> APIResponse<T> error(String code, String message, List<String> errors) {
        APIResponse<T> response = new APIResponse<>();
        response.setCode(code);
        response.setMessage(message);
        response.setErrors(errors);
        return response;
    }


}
