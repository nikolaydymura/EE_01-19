package edu.ee.http_secutity_demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

  @GetMapping(value = {"/", "/home"})
  public String indexPage() {
    return "home";
  }

  @GetMapping(value = {"/hello"})
  public String helloPage() {
    return "hello";
  }

  @GetMapping(value = {"/login"})
  public String loginPage() {
    return "login";
  }

  @GetMapping(value = {"/inner"})
  @PreAuthorize("hasRole('ROLE_ADMIN') and authentication.name == 'user1'")
  public String innerPage() {
    return "hello";
  }
}
