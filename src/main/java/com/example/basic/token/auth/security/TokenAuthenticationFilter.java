package com.example.basic.token.auth.security;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import com.example.basic.token.auth.domain.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

@Component
public class TokenAuthenticationFilter extends GenericFilterBean {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    final HttpServletRequest httpRequest = (HttpServletRequest) request;
    // Extract token from header
    final String accessToken = httpRequest.getHeader(AUTHORIZATION);

    // Get from DB and check whether token is valid
    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      if (StringUtils.hasText(accessToken) && user.getSecret().equals(accessToken)) {

        final UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        //Populate SecurityContextHolder by fetching relevant information using token
//      final User user = new User(
//          "username",
//          "password",
//          true,
//          true,
//          true,
//          true,
//          authorities);
//      final UsernamePasswordAuthenticationToken authentication =
//          new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
//      Authentication authentication = this.authenticationManager.authenticate(token);
//      SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    chain.doFilter(request, response);
  }
}
