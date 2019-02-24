package edu.ee.controllers;

import antlr.collections.impl.IntRange;
import edu.ee.dto.BookDTO;
import edu.ee.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

  @Autowired
  private BookService bookService;

  //http://localhost:8080/books
  @RequestMapping(method = RequestMethod.POST, path = "books",
          produces = {"application/json", "application/xml"})
  @ResponseStatus(code = HttpStatus.CREATED)
  public @ResponseBody
  BookDTO create(@Valid @RequestBody BookDTO bookToCreate) {
    return bookService.createBook(bookToCreate);
  }

  //   //http://localhost:8080/books/10
  @RequestMapping(method = RequestMethod.GET, path = "books/{id}",
          produces = {"application/json", "application/xml"})
  public @ResponseBody
  BookDTO getBook(@Valid @Min (0) @Max (1000) @PathVariable("id") int bookId) throws Exception {
    return bookService.getBook(bookId);
  }

  @RequestMapping(method = RequestMethod.GET, path = "books",
          produces = {"application/json", "application/xml"})
  public @ResponseBody
  List<BookDTO> getBooks(@RequestParam(value = "offset", required = false, defaultValue = "0") int offset,
                         @RequestParam(value = "limit", required = false, defaultValue = "20") int limit) {
    return bookService.getList();
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
    return bookService.updateBook(bookDTO);
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
    return bookService.updateBook(bookDTO);
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "books/{id}",
          produces = {"application/json", "application/xml"})
  public @ResponseBody
  BookDTO replaceBook(@PathVariable("id") int bookId) {
    return bookService.deleteBook(bookId);
  }
}
