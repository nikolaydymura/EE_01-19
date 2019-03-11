package edu.ee.books_view.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(value = "books-api-service")
public interface BooksService {
  @GetMapping("books")
  List<String> getBooks();
}
