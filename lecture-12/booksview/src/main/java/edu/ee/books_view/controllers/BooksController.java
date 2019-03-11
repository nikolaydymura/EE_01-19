package edu.ee.books_view.controllers;

import edu.ee.books_view.services.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

@RestController
public class BooksController {

  @Autowired
  private LoadBalancerClient client;

  @Autowired
  private BooksService booksService;

  @GetMapping("books")
  public List<String> listBooks() {

    ServiceInstance choose = client.choose("books-api-service");

    URI uri = choose.getUri();

    String url = uri.toString() + "/books";

    RestTemplate booksRequest = new RestTemplate();
    ResponseEntity<String[]> books = booksRequest
            .getForEntity(url, String[].class);
    return Arrays.asList(books.getBody());
  }

  @GetMapping("booksFeign")
  public List<String> listBooksFeign() {
    return booksService.getBooks();
  }

}
