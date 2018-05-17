package com.example.basic.token.auth.utils;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public class TokenUtils {

  public static final String extractCustomTokenFromHeader(HttpServletRequest httpRequest) {
    // Extract token from header
    final String tokenHeader = httpRequest.getHeader(AUTHORIZATION);
    return StringUtils.substringAfterLast(tokenHeader, ": ");
  }
}
