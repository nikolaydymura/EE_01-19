package edu.ee.oauth_2_client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class DemoController {

  @GetMapping("helloPrivate")
  public String helloPrivate(Principal principal) {
    return "Hello " + principal.getName();
  }

  @GetMapping("helloPublic")
  public String helloPublic() {
    return "Hello world";
  }
}
