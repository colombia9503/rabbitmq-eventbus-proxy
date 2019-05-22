package com.accenture.proxy.router;

import javax.naming.OperationNotSupportedException;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.accenture.proxy.error.service.InvalidServiceCandidateException;

public abstract class CommonRouteBuilder extends RouteBuilder {
	
	public CommonRouteBuilder(CamelContext context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public CommonRouteBuilder() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
	}
	
	public abstract void bindService() throws InvalidServiceCandidateException, Exception;
	
	public abstract void bindJsonmarshallers(JsonLibrary library) throws OperationNotSupportedException;
}
