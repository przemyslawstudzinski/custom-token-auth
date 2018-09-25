package com.example.basic.token.auth.web;

import javax.transaction.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Transactional
public class TokenController {

  @RequestMapping(value = "/token", method = RequestMethod.POST)
  public String getToken() {
    return "Token has been created successfully.";
  }
}
