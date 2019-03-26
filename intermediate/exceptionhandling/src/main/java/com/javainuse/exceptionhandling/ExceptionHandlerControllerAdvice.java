package com.javainuse.exceptionhandling;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * The ExceptionHandlerControllerAdvice will catch the exceptions thrown by the controller method, and we send more appropriate response to the caller.
 * For example if an exception occurs we don't want the caller to get the entire stack trace involving technical details.
 * Also we can return the appropriate response status depending on the business logic.
 *
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ExceptionResponse handleException(final Exception exception, final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		return error;
	}

	/**
	 * {
	 * 	message: "Employee not found",
	 * 	details: [ ],
	 * 	path: "/employee2"
	 * }
	 */
	@ExceptionHandler(value = { ResourceNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final ResourceNotFoundException exception, final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setMessage(exception.getMessage());
		error.setPath(request.getRequestURI());
		return error;
	}

//	@ExceptionHandler(javax.persistence.EntityNotFoundException.class)
//	protected ResponseEntity<Object> handleEntityNotFound(final EntityNotFoundException ex, final HttpServletRequest request) {
//		ExceptionResponse error = new ExceptionResponse();
//		error.setMessage(ex.getMessage());
//		error.setPath(request.getContextPath());
//		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
//	}

	/**
	 * Handle validation object like @NotEmpty and @Email,
	 * Enable validation of request body by @Valid annotation.
	 * {
	 *     "message": "Validation Failed",
	 *     "details": [
	 *         "last name must not be empty",
	 *         "email should be a valid email"
	 *     ]
	 * }
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> details = new ArrayList<>();
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ExceptionResponse error = new ExceptionResponse();
		error.setMessage("Validation Failed");
		error.setDetails(details);
		error.setPath(request.getContextPath());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setMessage("Malformed JSON request");
		error.setPath(request.getContextPath());
		return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
	}
}
