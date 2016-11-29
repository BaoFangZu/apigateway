
package com.huawei.it.hicloud.iam.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@Configuration
@EnableRedisHttpSession 
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DaoAuthenticationProvider daoAuthenticationProvider;
	
	@Autowired
	public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
		// @formatter:off	
		auth.authenticationProvider(daoAuthenticationProvider);
		// @formatter:on
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//.loginProcessingUrl("/auth/tokens")
		http.formLogin().loginPage("/api/tokens")
		.and()
			.logout()
		.and()
			.authorizeRequests()
			.antMatchers("/index.html","/logout","/api/*").permitAll()
			.and()
			.httpBasic().disable()
			.csrf().disable();
		
			http.headers().frameOptions().disable();
	}
	
}