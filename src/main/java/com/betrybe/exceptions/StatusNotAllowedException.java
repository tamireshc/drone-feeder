package com.betrybe.exceptions;

public class StatusNotAllowedException extends RuntimeException {
  public StatusNotAllowedException(String message) {
    super(message);
  }
}
