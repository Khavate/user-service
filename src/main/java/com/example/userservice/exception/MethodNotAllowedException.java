package com.example.userservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends RuntimeException {
	private static final Logger logger = LoggerFactory.getLogger(MethodNotAllowedException.class);	

	private static final long serialVersionUID = 1L;

	private int code;

	public MethodNotAllowedException(int code, String message) {
		super(message);
		this.setCode(code);
		logger.error(message);
	}

	public MethodNotAllowedException(int code, String message, Throwable ex) {
		super(message, ex);
		this.setCode(code);
		logger.error(message);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}	
}
