package com.sopheak.app.configuration.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer{

	@Override
	protected boolean enableHttpSessionEventPublisher() {
		// TODO Auto-generated method stub
		return true;
	}
}
