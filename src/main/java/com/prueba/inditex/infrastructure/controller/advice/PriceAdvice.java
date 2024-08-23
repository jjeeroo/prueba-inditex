package com.prueba.inditex.infrastructure.controller.advice;

import com.prueba.inditex.domain.exceptions.PriceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class PriceAdvice {

  @ExceptionHandler(PriceNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleResourceNotFoundException(PriceNotFoundException ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<ErrorDetails> handleRequestParameterException(MissingServletRequestParameterException ex,
      WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorDetails> handleGlobalException(Exception ex, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), request.getDescription(false));
    return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Getter
  @Setter
  @AllArgsConstructor
  public static class ErrorDetails {
    private String message;
    private String details;
  }
}
