package com.example.userservice.exception;

public class ApiError extends Exception {

	private static final long serialVersionUID = 1L;

	private int code;

	public ApiError(int code, String message) {
		super (message);
		this.setCode(code);
	}

	public ApiError(int code, String message, Throwable ex) {
		super (message, ex);
		this.setCode(code);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}	
}
