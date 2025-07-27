package com.abdul.ums.advice;

import com.abdul.ums.exception.NoUserFoundException;
import com.abdul.ums.exception.UserAlreadyExistException;
import com.abdul.ums.model.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoUserFoundException.class)
    public ResponseEntity<APIResponse<String>> handleNotFound(NoUserFoundException ex) {
        return new ResponseEntity<>(APIResponse.error(ex.getCode(), ex.getMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<APIResponse<String>> handleUserAlreadyExist(UserAlreadyExistException ex) {
        return new ResponseEntity<>(APIResponse.error(ex.getCode(), ex.getMessage()), HttpStatus.OK);
    }

}
