package edu.ee.booksviewservice;

import com.sun.javaws.exceptions.InvalidArgumentException;
import edu.ee.booksviewservice.dto.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

  private static final String BOOKS_URI = "http://127.0.0.1:7070/books";
  @LoadBalanced
  private RestTemplate restTemplate = new RestTemplate();

  public List<BookDTO> getBooks() {
    BookDTO[] books = restTemplate.getForObject(BOOKS_URI, BookDTO[].class);
    return Arrays.asList(books);
  }

  public BookDTO saveBook(BookDTO book) throws Exception {
    BookDTO bookDTO = Optional.ofNullable(book)
            .orElseThrow(() -> new InvalidArgumentException(new String[]{"book in null"}));

    return bookDTO;
  }
}
