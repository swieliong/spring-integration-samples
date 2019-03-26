package com.javainuse.exceptionhandling;

import java.util.ArrayList;
import java.util.List;

public class ExceptionResponse {

	private String message;
	private List<String> details = new ArrayList<>();
	private String path;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
