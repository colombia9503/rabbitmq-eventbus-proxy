package com.accenture.proxy.error.service;

public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6791164412405870265L;
	private int code;

	public ServiceException(int code, String message) {
		super(message);
		this.code = code;
	}
	
	public ServiceException(int code, String message, Throwable cause) {
		super(message, cause);
		this.code = code;
	}

	public int getCode() {
		return code;
	}

}
