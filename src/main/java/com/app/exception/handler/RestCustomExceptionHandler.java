package com.app.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.exception.ApiError;
import com.app.exception.ResourceNotFoundException;

@RestControllerAdvice
public class RestCustomExceptionHandler extends RestExceptionHandler {

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception) {
		return buildResponseEntity(HttpStatus.NOT_FOUND, exception);
	}

	protected ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, RuntimeException exception) {
		ApiError apiError = new ApiError(httpStatus, exception.getLocalizedMessage());
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
}
