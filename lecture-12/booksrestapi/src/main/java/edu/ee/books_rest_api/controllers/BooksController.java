package edu.ee.books_rest_api.controllers;

import edu.ee.books_rest_api.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class BooksController {

  @Autowired
  private Environment environment;

  @GetMapping("books")
  public List<String> listBooks() {
    String currentPort = environment.getProperty("local.server.port");
    List<String> books = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      books.add("Book " + i + " from " + currentPort);
    }
    return books;
  }

  @DeleteMapping("prettyBooks/{id}")
  public HttpEntity<?> deleteBook(@PathVariable("id") long book) {
    return new ResponseEntity<>(HttpStatus.OK);
  }


  @GetMapping("prettyBooks/{id}")
  public HttpEntity<BookDTO> singleBooks(@PathVariable("id") long book) {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setUid(book);
    bookDTO.setTitle("Mobby dick");

    bookDTO.add(linkTo(methodOn(BooksController.class).singleBooks(bookDTO.getUid())).withSelfRel());
    HttpEntity<?> invocationValue = methodOn(BooksController.class).deleteBook(bookDTO.getUid());
    bookDTO.add(linkTo(invocationValue).withSelfRel());

    return new ResponseEntity<>(bookDTO, HttpStatus.OK);
  }

  @GetMapping("prettyBooks")
  public HttpEntity<List<BookDTO>> allBooks() {

    BookDTO bookDTO = new BookDTO();
    bookDTO.setUid(1L);
    bookDTO.setTitle("Mobby dick");

    bookDTO.add(linkTo(methodOn(BooksController.class).singleBooks(bookDTO.getUid())).withSelfRel());

    List<BookDTO> bookDTOS = new ArrayList<>();
    bookDTOS.add(bookDTO);
    return new ResponseEntity<>(bookDTOS, HttpStatus.OK);
  }
}
