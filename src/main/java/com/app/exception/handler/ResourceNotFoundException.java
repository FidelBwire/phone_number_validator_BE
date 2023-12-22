package com.app.exception.handler;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -2140732187491061455L;

	public ResourceNotFoundException() {
		this("Resource Not found");
	}

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
