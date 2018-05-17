package com.example.basic.token.auth.security;

import com.example.basic.token.auth.domain.User;
import com.example.basic.token.auth.repository.UserRepository;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

//@Component
public class CustomBasicAuthenticationFilter extends BasicAuthenticationFilter {

  public static final String AUTHORIZATION_CUSTOM_TOKEN = "Authorization: Custom Token";

  @Autowired
  private UserRepository userRepository;

  @Autowired
  public CustomBasicAuthenticationFilter(
      AuthenticationManager authenticationManager) {
    super(authenticationManager);

  }

  @Override
  protected void onSuccessfulAuthentication(final javax.servlet.http.HttpServletRequest request,
      final javax.servlet.http.HttpServletResponse response, final Authentication authResult) {
    // Generate Token
    final String token = UUID.randomUUID().toString();

    // Save the token for the logged in user
    User user = (User) authResult.getPrincipal();
    user.setSecret(token);
    userRepository.save(user);

    // Send token in the response
    response.setHeader(AUTHORIZATION_CUSTOM_TOKEN, token);
  }
}
