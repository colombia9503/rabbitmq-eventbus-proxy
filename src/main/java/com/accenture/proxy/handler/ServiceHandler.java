package com.accenture.proxy.handler;

@FunctionalInterface
public interface ServiceHandler<T> {
	public void handle(T object);
}
