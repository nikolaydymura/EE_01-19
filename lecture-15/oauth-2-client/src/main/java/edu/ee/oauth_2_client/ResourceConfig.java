package edu.ee.oauth_2_client;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@EnableResourceServer
@EnableOAuth2Client
@Configuration
public class ResourceConfig extends ResourceServerConfigurerAdapter {

  /*@Override
  public void configure(HttpSecurity http) throws Exception {
    //http.authorizeRequests()
    //        .antMatchers("/hello").permitAll();
  }*/
}
