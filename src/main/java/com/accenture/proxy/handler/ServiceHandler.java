package com.accenture.proxy.handler;

@FunctionalInterface
public interface ServiceHandler {
	public <T> void handle(T object);
}
