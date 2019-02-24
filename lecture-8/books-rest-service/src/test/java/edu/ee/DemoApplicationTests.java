package edu.ee;

import edu.ee.controllers.BookController;
import edu.ee.dto.BookDTO;
import edu.ee.models.BookEntity;
import edu.ee.models.UserEntity;
import edu.ee.repositories.BookRepository;
import edu.ee.repositories.Magazine;
import edu.ee.repositories.MagazineRepository;
import edu.ee.repositories.Publisher;
import edu.ee.services.BookService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DemoApplicationTests {

  @MockBean
  private BookService bookService;

  @Autowired
  private BookController bookController;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private MagazineRepository magazineRepository;

  @Test
  public void testBookGetById() throws Exception {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setId(10);
    bookDTO.setTitle("Big Elephant");
    bookDTO.setAuthor("John Doe");

    Mockito.when(bookService.getBook(10)).thenReturn(bookDTO);

    BookDTO book = bookController.getBook(10);

    Mockito.verify(bookService, Mockito.times(1))
            .getBook(10);
    Mockito.verifyNoMoreInteractions(bookService);

    Assert.that(book.getId() == 10, "Id must be the same");
  }

  @Test
  public void testBookGetByIdIncorrectId() throws Exception {
    bookController.getBook(-1);
    Mockito.verifyZeroInteractions(bookService);
  }


  @Test
  @Sql(scripts = {"init.sql", "data.sql"})
  public void shouldReturnDefaultMessage() throws Exception {
    BookDTO bookDTO = new BookDTO();
    bookDTO.setId(10);
    bookDTO.setTitle("Big Elephant");
    bookDTO.setAuthor("John Doe");

    Mockito.when(bookService.getBook(10)).thenReturn(bookDTO);


    this.mockMvc.perform(get("/books/10"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.tit", Matchers.is("Big Elephant")));
  }
  @Test
  public void checkDatabase() throws Exception {
    BookEntity bookEntity1 = new BookEntity();
    bookEntity1.setAuthor("Tom");
    bookEntity1.setTitle("Jessica");

    BookEntity bookEntity2 = new BookEntity();
    bookEntity2.setAuthor("Sam");
    bookEntity2.setTitle("Johns");
    bookRepository.save(bookEntity1);
    bookRepository.save(bookEntity2);

    UserEntity userEntity = new UserEntity();
    userEntity.addBook(bookEntity1);
    userEntity.addBook(bookEntity2);

    BookEntity jessica = bookRepository.findBookEntityByTitle("Jessica");

    Assert.that(jessica.getId() == bookEntity1.getId(), "Id must be the same");
  }

  @Test
  public void dynamicInsert() throws Exception {
    BookEntity book1 = new BookEntity();
    book1.setTitle("Title only");
    bookRepository.save(book1);

    BookEntity book2 = new BookEntity();
    book2.setAuthor("Author only");
    bookRepository.save(book2);
  }

  @Test
  public void embededPublisher() throws Exception {
    Magazine mabeline = new Magazine();
    mabeline.setName("Mabeline");
    Publisher publisher = new Publisher();
    publisher.setLocation("Kharkiv");
    publisher.setName("HIRE");
    mabeline.setPublisher(publisher);
    magazineRepository.save(mabeline);
  }
}

