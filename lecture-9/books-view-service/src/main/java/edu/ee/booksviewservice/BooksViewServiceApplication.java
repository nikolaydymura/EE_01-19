package edu.ee.booksviewservice;

import edu.ee.booksviewservice.config.PageTitle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableConfigurationProperties(PageTitle.class)
public class BooksViewServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(BooksViewServiceApplication.class, args);
  }

}
