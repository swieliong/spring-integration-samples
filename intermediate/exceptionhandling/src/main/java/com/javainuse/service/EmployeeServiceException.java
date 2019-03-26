package com.javainuse.service;

public class EmployeeServiceException extends RuntimeException {

	private static final long serialVersionUID = -470180507998010368L;

	public EmployeeServiceException() {
		super();
	}

	public EmployeeServiceException(final String message) {
		super(message);
	}
}
