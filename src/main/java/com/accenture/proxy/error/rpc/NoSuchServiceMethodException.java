package com.accenture.proxy.error.rpc;

import java.lang.reflect.Method;

import org.apache.logging.log4j.message.StringFormattedMessage;

public class NoSuchServiceMethodException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5537551134923456844L;

	public <T> NoSuchServiceMethodException() {
		super("No such method for service call");
	}

	private <T> String formattedMessage(Method method, Class<T> clazz) {
		String message = new StringFormattedMessage("", method.getName(), clazz.getSimpleName()).toString();
		return message;
	}
}
