package com.example.basic.token.auth.util;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

public final class TokenUtils {

  /**
   * Extract token from Authorization header from request.
   * @param httpRequest httpRequest
   * @return token
   */
  public static String extractCustomTokenFromHeader(HttpServletRequest httpRequest) {
    final String tokenHeader = httpRequest.getHeader(AUTHORIZATION);
    return StringUtils.substringAfterLast(tokenHeader, ": ");
  }
}
