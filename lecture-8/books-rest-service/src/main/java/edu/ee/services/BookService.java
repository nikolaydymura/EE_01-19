package edu.ee.services;

import edu.ee.dto.BookDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

  public BookDTO createBook(BookDTO bookDTO) {
    throw new UnsupportedOperationException();
  }

  public BookDTO updateBook(BookDTO bookDTO) {
    throw new UnsupportedOperationException();
  }

  public BookDTO deleteBook(int bookId) {
    throw new UnsupportedOperationException();
  }

  public BookDTO getBook(int bookId) {
    throw new UnsupportedOperationException();
  }

  public List<BookDTO> getList() {
    ArrayList<BookDTO> bookDTOS = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      BookDTO book = new BookDTO();
      book.setId(i);
      book.setTitle("Title " + i);
      book.setAuthor("Author " + i);
      bookDTOS.add(book);
    }
    return bookDTOS;
  }
}
