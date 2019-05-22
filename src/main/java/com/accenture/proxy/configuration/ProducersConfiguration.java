package com.accenture.proxy.configuration;

import org.apache.camel.CamelContext;
import org.apache.camel.FluentProducerTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducersConfiguration {
	@Bean
	public FluentProducerTemplate fluentProducerTemplate(CamelContext camelContext) {
		return camelContext.createFluentProducerTemplate();
	}
}
