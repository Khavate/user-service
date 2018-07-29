package com.example.userservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

	private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);	

 	private static final long serialVersionUID = 1L;

	private int code;

	public ResourceNotFoundException(int code, String message) {
		super(message);
		this.setCode(code);
		logger.error(message);
	}

	public ResourceNotFoundException(int code, String message, Throwable ex) {
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