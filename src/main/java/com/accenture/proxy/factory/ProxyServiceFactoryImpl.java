package com.accenture.proxy.factory;

import java.util.HashSet;

import org.apache.camel.CamelContext;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.accenture.proxy.error.service.InvalidServiceCandidateException;
import com.accenture.proxy.router.CommonRouteBuilder;
import com.accenture.proxy.router.DefaultServiceRouteBuilder;

@Service
public class ProxyServiceFactoryImpl implements ProxyServiceFactory, ApplicationContextAware {

	private HashSet<CommonRouteBuilder> commonRouteBuilders;
	private CamelContext camelContext;
	private ApplicationContext applicationContext;

	public ProxyServiceFactoryImpl(CamelContext camelContext) {
		this.camelContext = camelContext;
		commonRouteBuilders = new HashSet<>();
	}

	@Override
	public <T> void createRouterForService(Class<T> clazz, T instance) throws InvalidServiceCandidateException, Exception {
		DefaultServiceRouteBuilder<?> serviceRouteBuilder = applicationContext.getBean(DefaultServiceRouteBuilder.class, camelContext, clazz, instance);
//		CommonRouteBuilder routeBuilder = new DefaultServiceRouteBuilder<T>(camelContext, clazz, instance);
//		serviceRouteBuilder.bindService();
		commonRouteBuilders.add(serviceRouteBuilder);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
