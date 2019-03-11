package edu.ee;

import edu.ee.aspects.AspectsService;
import edu.ee.aspects.TargetService;
import edu.ee.controllers.BookController;
import edu.ee.dto.BookDTO;
import edu.ee.models.BookEntity;
import edu.ee.models.UserEntity;
import edu.ee.repositories.*;
import edu.ee.services.BookService;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BooksRestApplicationTests {

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

  @PersistenceContext
  private EntityManager defaultManager;

  @Autowired
  private AspectsService aspectsService;

  @Autowired
  private TargetService targetService;

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

    Assert.assertEquals(book.getId(), 10);
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

    Assert.assertEquals(jessica.getId(), bookEntity1.getId());
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

  @Test
  @Transactional(propagation = Propagation.REQUIRES_NEW)
  public void attachMerge() throws Exception {
    BookEntity book = new BookEntity();
    book.setTitle("John");

    defaultManager.persist(book);
    defaultManager.flush();

    defaultManager.detach(book);

    book = defaultManager.find(BookEntity.class, book.getId());

    book.setTitle("Bob");

    defaultManager.persist(book);
    defaultManager.flush();
    //offset=0&limit=20
    //page=<token_hash>
    //startDate=now&endDate=yesterday
    //startIdx=<idx>&endIdx=<idx>
    Pageable p;


  }

  @Test
  public void beforeDoSomething() {
    targetService.doSomething();
    targetService.doSomething1();
    try {
      long aLong = targetService.doSomething2(100);
      Assert.assertEquals( aLong, 100 + 200);
      targetService.doSomething4(100);
      targetService.doSomething2(-1);
      targetService.doSomething4(-1);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

