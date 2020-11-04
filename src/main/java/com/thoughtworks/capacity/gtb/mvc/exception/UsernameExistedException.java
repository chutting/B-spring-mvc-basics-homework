package com.thoughtworks.capacity.gtb.mvc.exception;

public class UsernameExistedException extends RuntimeException {
  public UsernameExistedException(String message) {
    super(message);
  }
}
