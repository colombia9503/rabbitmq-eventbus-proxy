package com.accenture.proxy.binding;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.accenture.proxy.factory.ProxyServiceFactory;
import com.accenture.proxy.handler.ServiceHandler;

@Component
public class ServiceBinder implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	private ProxyServiceFactory proxyServiceFactory;
	
	public ServiceBinder(ProxyServiceFactory proxyServiceFactory) {
		this.proxyServiceFactory = proxyServiceFactory;
	}
	
	public <T> void bindServiceInstance(Class<T> clazz, ServiceHandler handler) {
		T serviceInstance = applicationContext.getBean(clazz);
		proxyServiceFactory.createRouterForService(clazz, serviceInstance);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
