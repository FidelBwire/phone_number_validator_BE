package com.app.exception;

import org.springframework.http.HttpStatusCode;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApiError {
	private HttpStatusCode status;
	private int code;
	private Long timestamp = System.currentTimeMillis();
	private String message;
	@JsonIgnore
	private String debugMessage;
	private Object details;

	public ApiError() {
	}

	public ApiError(HttpStatusCode status) {
		super();
		this.status = status;
		this.code = status.value();
	}

	public ApiError(HttpStatusCode status, String message) {
		super();
		this.status = status;
		this.code = status.value();
		this.message = message;
	}

	public ApiError(HttpStatusCode status, String message, Object details) {
		super();
		this.status = status;
		this.code = status.value();
		this.message = message;
		this.details = details;
	}

	public ApiError(HttpStatusCode status, String message, Throwable ex) {
		this();
		this.status = status;
		this.code = status.value();
		this.message = message;
		this.debugMessage = ex.getMessage();
	}

	public ApiError(HttpStatusCode status, String message, Throwable ex, Object details) {
		this();
		this.status = status;
		this.code = status.value();
		this.message = message;
		this.details = details;
		this.debugMessage = ex.getMessage();
	}

	public HttpStatusCode getStatus() {
		return status;
	}

	public void setStatus(HttpStatusCode status) {
		this.status = status;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDebugMessage() {
		return debugMessage;
	}

	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}

	public Object getDetails() {
		return details;
	}

	public void setDetails(Object details) {
		this.details = details;
	}

}
