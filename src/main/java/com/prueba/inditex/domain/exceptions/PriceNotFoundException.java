package com.prueba.inditex.domain.exceptions;

public class PriceNotFoundException extends RuntimeException {
  private String message;

  public PriceNotFoundException(String message) {
    super(message);
  }
}
