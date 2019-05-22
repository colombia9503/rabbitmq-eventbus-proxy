package com.accenture.proxy.service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.accenture.proxy.integration.MessagingPlatformType;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ProxyService {
	String serviceName();
	String value();
}
