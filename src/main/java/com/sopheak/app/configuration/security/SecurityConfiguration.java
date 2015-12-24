package com.sopheak.app.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sopheak.app.service.implement.CustomUserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CustomUserDetailServiceImpl userDetailsService;
	
	@Autowired
	@Qualifier(value="ajaxAuthenticationSuccessHandler")
	private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
	
	@Autowired
	@Qualifier(value="ajaxAuthenticationFailureHandler")
	private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*auth.inMemoryAuthentication()
			.withUser("admin")
			.password("admin")
			.roles("ADMIN" , "USER");
		
		auth.inMemoryAuthentication()
			.withUser("user")
			.password("user")
			.roles("USER");*/
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
		
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/user/**").hasAnyRole("USER" , "ADMIN")
			.antMatchers("/admin/**").hasRole("ADMIN");
//			.antMatchers("/user/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_ADMIN')")
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')");
		http
			.formLogin()
			.permitAll()
			.loginPage("/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(ajaxAuthenticationSuccessHandler)
			.failureHandler(ajaxAuthenticationFailureHandler);
		http
			.sessionManagement()
			.sessionAuthenticationErrorUrl("/login")
			.maximumSessions(1)
			.expiredUrl("/login")
			.sessionRegistry(sessionRegistryImpl());
		http
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout")
			.invalidateHttpSession(true)
			.deleteCookies("JESSIONID")
			.permitAll();
		http.csrf().disable();
		http.exceptionHandling().accessDeniedPage("/accessDenied");
	}

	@Bean
	protected SessionRegistry sessionRegistryImpl(){
		return new SessionRegistryImpl();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}

