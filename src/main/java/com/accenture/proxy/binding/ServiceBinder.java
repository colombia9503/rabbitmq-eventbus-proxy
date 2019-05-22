package com.accenture.proxy.binding;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;
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
	
	public <T> void bindServiceInstance(Class<T> clazz, ServiceHandler<Boolean> handler) {
		T instance = applicationContext.getBean(clazz);
		try {
			proxyServiceFactory.createRouterForService(clazz, instance);
			handler.handle(true);
		} catch (Exception e) {
			e.printStackTrace();
			handler.handle(false);
		}
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
