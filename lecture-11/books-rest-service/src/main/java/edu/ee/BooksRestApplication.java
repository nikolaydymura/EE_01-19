package edu.ee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@EnableDiscoveryClient
@SpringBootApplication
@Configuration
public class BooksRestApplication {

  public static void main(String[] args) {
    SpringApplication.run(BooksRestApplication.class, args);
  }
}

