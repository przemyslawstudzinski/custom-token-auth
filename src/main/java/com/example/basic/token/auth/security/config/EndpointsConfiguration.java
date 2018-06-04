package com.example.basic.token.auth.security.config;

import com.example.basic.token.auth.security.TokenAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@Order(2)
public class EndpointsConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    // No session will be created or used by spring security.
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Disable CSRF (cross site request forgery).
    http.csrf().disable();

    // Entry point configuration.
    http.antMatcher("/**")
        .authorizeRequests()
        .anyRequest()
        .authenticated();

    // Implementing Token based authentication in this filter
    http.addFilterBefore(tokenAuthenticationFilter(), BasicAuthenticationFilter.class);
  }

  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() {
    return new TokenAuthenticationFilter();
  }

}
