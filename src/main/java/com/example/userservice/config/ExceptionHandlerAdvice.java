package com.example.userservice.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.userservice.exception.ApiError;
import com.example.userservice.exception.MethodNotAllowedException;
import com.example.userservice.exception.ResourceNotFoundException;
import com.example.userservice.exception.UnauthorizedException;
import com.example.userservice.exception.UserServiceException;
import com.fasterxml.jackson.core.JsonParseException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);	

	@ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
	public ApiError resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request,
			HttpServletResponse response) {
		logger.error("Request: " + request.getRequestURL() + " exception " + ex, ex);
		
		return new ApiError(HttpStatus.NOT_FOUND.value(), ex.getMessage(), ex);
	}

	@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Unauthorized")
    @ExceptionHandler(UnauthorizedException.class)
	public ApiError unauthorizedException(UnauthorizedException ex, HttpServletRequest request,
			HttpServletResponse response) {
		 logger.error("Request: " + request.getRequestURL() + " exception " + ex, ex);

		return new ApiError(HttpStatus.UNAUTHORIZED.value(), ex.getMessage(), ex);
	}

	@ResponseBody
	@ExceptionHandler(MethodNotAllowedException.class)
    public ApiError methodNotAllowedException(MethodNotAllowedException ex, HttpServletRequest request,
			HttpServletResponse response) {
		logger.error("Request: " + request.getRequestURL() + " exception " + ex, ex);
		response.setStatus(HttpStatus.METHOD_NOT_ALLOWED.value());

		return new ApiError(HttpStatus.METHOD_NOT_ALLOWED.value(), ex.getMessage(), ex);
	}
	
    @ResponseStatus(value= HttpStatus.UNSUPPORTED_MEDIA_TYPE, reason="Unsupported Media Type")
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public void badMedia(HttpMediaTypeNotSupportedException ex, HttpServletRequest request) {
        logger.error("Request: " + request.getHeader("Content-Type") + " exception " + ex, ex);
    }

    @ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Bad Request")
    @ExceptionHandler({HttpMessageNotReadableException.class, JsonParseException.class, DataIntegrityViolationException.class,
            TypeMismatchException.class})
    public void badRequest(Exception ex, HttpServletRequest request) {
        logger.error("Request: " + request.getRequestURL() + " exception " + ex, ex);
    }
    
	// Service specific exception
    @ResponseBody
	@ExceptionHandler({Exception.class, UserServiceException.class})
	public ApiError UserServiceException(Exception ex, HttpServletRequest request,
			HttpServletResponse response) {
		 logger.error("Request: " + request.getRequestURL() + " exception " + ex, ex);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

		return new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), ex);
	}
}
