package com.example.basic.token.auth.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  @Qualifier(value = "UserDetailsServiceImpl")
  private UserDetailsService userDetailsService;

  @Bean
  public CustomBasicAuthenticationFilter customBasicAuthenticationFilter() throws Exception {
    return new CustomBasicAuthenticationFilter(this.authenticationManager());
  }

  @Bean
  public TokenAuthenticationFilter tokenAuthenticationFilter() throws Exception {
    return new TokenAuthenticationFilter();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  public void configure(final HttpSecurity http) throws Exception {
    http.httpBasic()
        .and()
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .csrf()
        .disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    // Implementing Token based authentication in this filter
    http.addFilterBefore(tokenAuthenticationFilter(), BasicAuthenticationFilter.class);

    // Creating token when basic authentication is successful
    // and the same token can be used to authenticate for further requests
    http.addFilter(customBasicAuthenticationFilter());
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
  }
}
