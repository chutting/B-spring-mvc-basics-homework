package com.thoughtworks.capacity.gtb.mvc.exception;

public class UsernameOrPasswordError extends RuntimeException{
  public UsernameOrPasswordError(String message) {
    super(message);
  }
}
