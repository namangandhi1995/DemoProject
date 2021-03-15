package com.test.demo.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NodesExceptionHandler {

	@ExceptionHandler({ RuntimeException.class })
	public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
		return error(HttpStatus.INTERNAL_SERVER_ERROR, e);
	}

	@ExceptionHandler({ FundNotFoundException.class, InvestorNotFoundException.class })
	public ResponseEntity<String> handleNotFoundException(Exception e) {
		return error(HttpStatus.NOT_FOUND, e);
	}
	
	@ExceptionHandler({InvalidInputException.class})
	public ResponseEntity<String> handleInvalidArgumentsExceptions(Exception e) {
		return error(HttpStatus.UNPROCESSABLE_ENTITY, e);
	}
	private ResponseEntity<String> error(HttpStatus status, Exception e) {
		return ResponseEntity.status(status).body(e.getMessage());
	}
}
