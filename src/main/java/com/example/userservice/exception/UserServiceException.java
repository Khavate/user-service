package com.example.userservice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserServiceException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(UserServiceException.class);	

	private int code;

	public UserServiceException(int code, String message) {
		super(message);
		this.setCode(code);
		logger.error(message);
	}

	public UserServiceException(int code, String message, Throwable ex) {
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
