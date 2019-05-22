package com.accenture.proxy.router;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.naming.OperationNotSupportedException;

import org.apache.camel.CamelContext;
import org.apache.camel.model.ChoiceDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.accenture.proxy.error.rpc.NoSuchServiceMethodException;
import com.accenture.proxy.error.service.InvalidServiceCandidateException;
import com.accenture.proxy.integration.MessagingPlatformType;
import com.accenture.proxy.service.ProxyService;

@Component
@Scope("prototype")
public class DefaultServiceRouteBuilder<T> extends CommonRouteBuilder {

	private static final Logger log = LoggerFactory.getLogger(DefaultServiceRouteBuilder.class);

	private Class<T> clazz;
	private T instance;

	public DefaultServiceRouteBuilder(CamelContext camelContext, Class<T> clazz, T instance) {
		super(camelContext);
		this.clazz = clazz;
		this.instance = instance;
	}
	
	@PostConstruct
	@Override
	public void bindService() throws Exception {
		log.debug("Binding service class {}", clazz.getName());
		ProxyService proxyServiceAnnotation = clazz.getAnnotation(ProxyService.class);
		if (!Optional.ofNullable(proxyServiceAnnotation).isPresent()) {
			throw new InvalidServiceCandidateException(
					"Service candidates must be anotated with com.accenture.service.ProxyService annotation.");
		}

		String serviceName = proxyServiceAnnotation.serviceName();
		String broker = proxyServiceAnnotation.value();

		Method[] serviceMethods = clazz.getDeclaredMethods();

		RouteDefinition baseDefiniton = from(broker + ":" + serviceName);

		ChoiceDefinition serviceChoiceDefinition = baseDefiniton.choice();

		for (Method method : serviceMethods) {
			serviceChoiceDefinition.when(header("method").isEqualTo(method.getName())).bean(instance, method.getName());

		}

		serviceChoiceDefinition.otherwise().process(exchange -> {
			exchange.getIn().setBody(new NoSuchServiceMethodException());
		}).endChoice();

		this.getContext().addRouteDefinition(baseDefiniton);
	}

	@Override
	public void bindJsonmarshallers(JsonLibrary library) throws OperationNotSupportedException {
		throw new OperationNotSupportedException("Not implemented yet");
	}

	private String bindedServiceName() {
		ProxyService proxyService = clazz.getAnnotation(ProxyService.class);
		return proxyService.serviceName();
	}
}
