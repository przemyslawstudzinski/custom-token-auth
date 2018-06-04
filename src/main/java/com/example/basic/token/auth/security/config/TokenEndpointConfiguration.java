package com.example.basic.token.auth.security.config;

import com.example.basic.token.auth.security.CustomBasicAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@Order(1)
public class TokenEndpointConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  public void configure(final HttpSecurity http) throws Exception {

    // No session will be created or used by spring security.
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Disable CSRF (cross site request forgery).
    http.csrf().disable();

    // Entry point configuration.
    http
        .antMatcher("/api/token")
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .httpBasic();

    // Creating token when basic authentication is successful
    // and the same token can be used to authenticate for further requests
    http.addFilter(customBasicAuthenticationFilter());
  }

  @Bean
  public CustomBasicAuthenticationFilter customBasicAuthenticationFilter() throws Exception {
    return new CustomBasicAuthenticationFilter(this.authenticationManager());
  }
}
