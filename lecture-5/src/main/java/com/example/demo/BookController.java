package com.example.demo;

import antlr.collections.impl.IntRange;
import com.example.demo.dto.BookDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@RestController
public class BookController {

  private final ThreadLocalRandom random = ThreadLocalRandom.current();

  @RequestMapping(method = RequestMethod.POST, path = "books",
          produces = {"application/json", "application/xml"})
  @ResponseStatus(code = HttpStatus.CREATED)
  public @ResponseBody
  BookDTO create(@Valid @RequestBody BookDTO bookToCreate) {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setAuthor(bookToCreate.getAuthor());
    bookDTO.setTitle(bookToCreate.getTitle());
    bookDTO.setId(random.nextInt(1000));
    return bookDTO;
  }

  //   /books/10
  @RequestMapping(method = RequestMethod.GET, path = "books/{id}",
          produces = {"application/json", "application/xml"})
  public @ResponseBody
  BookDTO getBook(@Valid @Min (0) @Max (1000) @PathVariable("id") int bookId) throws Exception {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setAuthor("Tom");
    bookDTO.setTitle("Johns");
    bookDTO.setId(bookId);
    return bookDTO;
  }

  @RequestMapping(method = RequestMethod.GET, path = "books",
          produces = {"application/json", "application/xml"})
  public @ResponseBody
  List<BookDTO> getBooks(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                         @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {
    List<BookDTO> books = new ArrayList<>();
    for (int i = offset; i < limit; i++) {
      BookDTO bookDTO = new BookDTO();
      bookDTO.setAuthor("Tom");
      bookDTO.setTitle("Johns");
      bookDTO.setId(i);
      books.add(bookDTO);
    }
    return books;
  }

  @RequestMapping(method = RequestMethod.PUT, path = "books/{id}",
          produces = {"application/json", "application/xml"})
  public @ResponseBody
  BookDTO replaceBook(@PathVariable("id") int bookId, @RequestBody
          BookDTO newBook) {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setAuthor(newBook.getAuthor());
    bookDTO.setTitle(newBook.getTitle());
    bookDTO.setId(bookId);
    return bookDTO;
  }

  @RequestMapping(method = RequestMethod.PATCH, path = "books/{id}",
          produces = {"application/json", "application/xml"})
  public @ResponseBody
  BookDTO updateBook(@PathVariable("id") int bookId, @RequestBody
          BookDTO newBook) {
    BookDTO bookFromStorage = new BookDTO();
    bookFromStorage.setAuthor("Tom");
    bookFromStorage.setTitle("Johns");
    bookFromStorage.setId(bookId);

    BookDTO bookDTO = new BookDTO();

    if (newBook.getAuthor() != null) {
      bookDTO.setAuthor(newBook.getAuthor());
    } else {
      bookDTO.setAuthor(bookFromStorage.getAuthor());
    }
    if (newBook.getTitle() != null) {
      bookDTO.setTitle(newBook.getTitle());
    } else {
      bookDTO.setTitle(bookFromStorage.getTitle());
    }
    bookDTO.setId(bookId);
    return bookDTO;
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "books/{id}",
          produces = {"application/json", "application/xml"})
  public @ResponseBody
  BookDTO replaceBook(@PathVariable("id") int bookId) {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setAuthor("Tom");
    bookDTO.setTitle("Book");
    bookDTO.setId(bookId);
    return bookDTO;
  }
}
