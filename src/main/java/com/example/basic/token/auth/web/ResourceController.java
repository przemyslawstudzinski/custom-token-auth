package com.example.basic.token.auth.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

  @RequestMapping(value = "/create", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public String create() {
    return "CREATED";
  }

  @RequestMapping(value = "/edit", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public String edit() {
    return "EDITED";
  }

  @RequestMapping(value = "/view", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public String view() {
    return "VIEW";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.GET)
  @ResponseStatus(HttpStatus.OK)
  public String delete() {
    return "DELETED";
  }



}
