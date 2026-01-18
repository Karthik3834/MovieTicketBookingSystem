package com.ey.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidException(MethodArgumentNotValidException ex){
		
		Map<String, List<String>> body = new HashMap<>();
		
		List<String> errors = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(DefaultMessageSourceResolvable::getDefaultMessage)
				.collect(Collectors.toList());
		body.put("errors",errors);
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
					
	}

	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DuplicateUserException.class)
	public ResponseEntity<Object> handleDuplicateUserException(DuplicateUserException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserDeactivatedException.class)
	public ResponseEntity<Object> handleUserDeactivatedException(UserDeactivatedException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<Object> handleMovieNotFoundException(MovieNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TheatreNotFoundException.class)
	public ResponseEntity<Object> handleTheatreNotFoundException(TheatreNotFoundException ex){
		return new ResponseEntity<>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
