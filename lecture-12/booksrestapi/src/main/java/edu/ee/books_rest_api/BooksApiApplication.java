package edu.ee.books_rest_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BooksApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(BooksApiApplication.class, args);
  }
}
