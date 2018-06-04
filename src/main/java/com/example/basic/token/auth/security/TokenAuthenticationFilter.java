package com.example.basic.token.auth.security;

import static com.example.basic.token.auth.util.TokenUtils.extractCustomTokenFromHeader;

import com.example.basic.token.auth.domain.User;
import com.example.basic.token.auth.repository.UserRepository;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

public class TokenAuthenticationFilter extends GenericFilterBean {

  @Autowired
  private UserRepository userRepository;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    final HttpServletRequest httpRequest = (HttpServletRequest) request;
    final String token = extractCustomTokenFromHeader(httpRequest);

    if (SecurityContextHolder.getContext().getAuthentication() == null
        && StringUtils.hasText(token)) {
      // Get from DB and check whether token is valid
      Optional<User> user = userRepository.findBySecret(token);

      if (StringUtils.hasText(token) && user.isPresent()) {
        //Populate SecurityContextHolder by fetching relevant information using token
        final UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(user.get(),
                null, user.get().getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }
    chain.doFilter(request, response);
  }

}
