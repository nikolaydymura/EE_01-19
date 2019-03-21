package edu.ee.http_secutity_demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
/*    auth.jdbcAuthentication()
            .usersByUsernameQuery("select username, password, enabled from users where username=$1")
            .authoritiesByUsernameQuery("select rolename from roles where username=$1");*/
    auth.userDetailsService(userDetailsService())
            .passwordEncoder(passwordEncoder());
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()

            .antMatchers("/", "/home", "/inner").permitAll()

            .anyRequest().authenticated()

            .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()

            .and()
            .logout()
            .permitAll();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    return new DemoUserDetailsService(usersService());
  }

  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  public UsersService usersService(){
    return new UsersService();
  }
}