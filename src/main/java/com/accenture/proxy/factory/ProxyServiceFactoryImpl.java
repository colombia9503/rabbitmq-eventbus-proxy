package com.accenture.proxy.factory;

import java.util.HashSet;

import org.apache.camel.CamelContext;
import org.springframework.stereotype.Service;

import com.accenture.proxy.router.CommonRouteBuilder;
import com.accenture.proxy.router.DefaultServiceRouteBuilder;

@Service
public class ProxyServiceFactoryImpl implements ProxyServiceFactory {

	private HashSet<CommonRouteBuilder> commonRouteBuilders;
	private CamelContext camelContext;

	public ProxyServiceFactoryImpl(CamelContext camelContext) {
		this.camelContext = camelContext;
		commonRouteBuilders = new HashSet<>();
	}

	@Override
	public <T> void createRouterForService(Class<T> clazz, T instance) {
		CommonRouteBuilder routeBuilder = new DefaultServiceRouteBuilder<T>(camelContext, clazz, instance);
		commonRouteBuilders.add(routeBuilder);
	}

}
