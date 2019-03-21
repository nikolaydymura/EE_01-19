package edu.ee.http_secutity_demo;

import edu.ee.http_secutity_demo.models.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class DemoUserDetailsService implements UserDetailsService {
  private UsersService usersService;

  public DemoUserDetailsService(UsersService usersService) {
    this.usersService = usersService;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    UserEntity user = usersService.getUser(userName);

    List<String> authoritiesList = usersService.getAuthorities(userName);
    List<GrantedAuthority> authorities = authoritiesList.stream()
            .map(SimpleGrantedAuthority::new)
    .collect(Collectors.toList());

    UserDetails userDetails = new User(user.getUserName(),
            user.getPassword(), authorities);
    return userDetails;
  }
}
