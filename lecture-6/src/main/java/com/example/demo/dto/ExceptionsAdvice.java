package com.example.demo.dto;

import com.example.demo.MyError;
import com.example.demo.UnknownException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionsAdvice {
  @ExceptionHandler({UnknownException.class, MethodArgumentNotValidException.class})
  public @ResponseBody
  ResponseEntity<MyError> handle(Exception e) {
    MyError myError = new MyError();
    myError.setResponseCode(HttpStatus.BAD_REQUEST.value());
    myError.setMessage(e.getMessage());

    ResponseEntity<MyError> error = new ResponseEntity<>(myError, HttpStatus.BAD_REQUEST);
    return error;
  }
}
