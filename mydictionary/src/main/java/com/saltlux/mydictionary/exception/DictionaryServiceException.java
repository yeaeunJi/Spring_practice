package com.saltlux.mydictionary.exception;

public class DictionaryServiceException  extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DictionaryServiceException() {
		super("DictionaryServiceException");
	}

	public DictionaryServiceException(String msg) {
		super(msg);
	}
}
