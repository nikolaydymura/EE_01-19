package edu.ee.books_registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class BooksRegistryApplication {
  public static void main(String[] args) {
    SpringApplication.run(BooksRegistryApplication.class, args);
  }
}
