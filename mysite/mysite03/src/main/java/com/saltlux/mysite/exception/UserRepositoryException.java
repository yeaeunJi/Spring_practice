package com.saltlux.mysite.exception;


public class UserRepositoryException extends RuntimeException {
	// RuntimeExceptionWrapper 
	private static final long serialVersionUID = 1L;

	public UserRepositoryException() {
		super("UserRepositoryException");
	}

	public UserRepositoryException(String msg) {
		super(msg);
	}
}
