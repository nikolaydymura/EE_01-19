package edu.ee.http_secutity_demo;

import edu.ee.http_secutity_demo.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersService {
  private static final Map<String, UserEntity> users = new HashMap<>();

  @Autowired
  private PasswordEncoder passwordEncoder;

  public UserEntity getUser(String username) {
    UserEntity userEntity = users.get(username);
    if (userEntity == null) {
      UserEntity newUserEntity = new UserEntity();
      newUserEntity.setUserName(username);
      newUserEntity.setPassword(passwordEncoder.encode(username));
      users.put(username, newUserEntity);
      return newUserEntity;
    }
    return userEntity;
  }

  public List<String> getAuthorities(String username) {
    if (username.equals("user1") || username.equals("user2")) {
      return Arrays.asList("ROLE_ADMIN");
    } else {
      return Arrays.asList("ROLE_USER");
    }
  }
}
