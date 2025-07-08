package com.victoria.wisedemo.api;

import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAll(Exception ex) {
        ApiError err = new ApiError("Server error", ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(
    MethodArgumentNotValidException ex,
    HttpHeaders headers,
    HttpStatusCode status,
    WebRequest request
) {
        String msg = ex.getBindingResult().getFieldErrors().stream()
                       .map(e -> e.getField() + ": " + e.getDefaultMessage())
                       .reduce((a, b) -> a + ", " + b)
                       .orElse("Validation failed");
        ApiError err = new ApiError("Validation failed", msg);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
}
