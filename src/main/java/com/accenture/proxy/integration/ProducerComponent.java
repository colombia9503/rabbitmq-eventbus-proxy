package com.accenture.proxy.integration;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.camel.Exchange;
import org.apache.camel.FluentProducerTemplate;
import org.apache.camel.util.json.JsonObject;
import org.springframework.stereotype.Component;

import com.accenture.proxy.error.service.ServiceException;

@Component
public class ProducerComponent {

	private FluentProducerTemplate fluentProducerTemplate;

	public ProducerComponent(FluentProducerTemplate fluentProducerTemplate) {
		this.fluentProducerTemplate = fluentProducerTemplate;
	}

	public <T> JsonObject sendMessage(T message, String serviceName, String func,
			MessagingPlatformType messagingPlatformType) throws ServiceException {
		Future<Object> result = fluentProducerTemplate.to(messagingPlatformType.getUri() + ":" + serviceName)
				.withHeader("method", func).withBody(message).asyncRequest();

		try {
			if (result.get() instanceof JsonObject) {
				return (JsonObject) result.get();
			} else if (result.get() instanceof ServiceException) {
				throw (ServiceException) result.get();
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return new JsonObject();
	}

}
