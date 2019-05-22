package com.accenture.proxy.integration;

public enum MessagingPlatformType {
	RABBITMQ("rabbitmq");
	private String uri;

	private MessagingPlatformType(String uri) {
		this.uri = uri;
	}

	public String getUri() {
		return uri;
	}

}
