package com.thoughtworks.capacity.gtb.mvc;

import com.thoughtworks.capacity.gtb.mvc.exception.UsernameExistedException;
import com.thoughtworks.capacity.gtb.mvc.exception.UsernameOrPasswordError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(UsernameExistedException.class)
  public ResponseEntity<ErrorResult> handle(UsernameExistedException ex) {
    ErrorResult errorResult = new ErrorResult(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
  }

  @ExceptionHandler(UsernameOrPasswordError.class)
  public ResponseEntity<ErrorResult> handle(UsernameOrPasswordError ex) {
    ErrorResult errorResult = new ErrorResult(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResult);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResult> handle(MethodArgumentNotValidException ex) {
    String message = ex.getBindingResult().getFieldError().getDefaultMessage();
    ErrorResult errorResult = new ErrorResult(message, HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResult> handle(ConstraintViolationException ex) {
    Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();

    String message = "";
    for (ConstraintViolation<?> constraint : ex.getConstraintViolations()) {
      message = constraint.getMessage();
      break;
    }
    ErrorResult errorResult = new ErrorResult(message, HttpStatus.BAD_REQUEST.value());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResult);
  }
}
