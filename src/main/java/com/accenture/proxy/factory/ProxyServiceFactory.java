package com.accenture.proxy.factory;

public interface ProxyServiceFactory {
	public <T> void createRouterForService(Class<T> clazz, T instance);
}
