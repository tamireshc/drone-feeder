package com.betrybe.exceptions;

public class NotFoundException extends RuntimeException{
  public NotFoundException(String message) {
    super(message);
  }
}
