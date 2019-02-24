package edu.ee.booksviewservice;

import edu.ee.booksviewservice.dto.BookDTO;
import org.hamcrest.Matchers;
import org.hamcrest.core.IsNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BooksServiceTests {

  @Autowired
  private BookService service;

  private List<BookDTO> books;

  @Before
  public void fetchBooks() {
    books = service.getBooks();
  }

  @Test
  public void checkBooksExists() {
    Assert.assertThat(books, IsNull.notNullValue());
  }

  @Test
  public void checkBooksDefaultLength10() {
    Assert.assertThat(books.size(), Matchers.equalTo(10));
  }

  @Test
  public void checkFirstBookHasId_0() {
    Assert.assertThat(books.get(0).getId(), Matchers.equalTo(0));
  }

  @Test
  public void checkFirstBookHasTitle_Title0(){
    Assert.assertThat(books.get(0).getTitle(), Matchers.equalTo("Title 0"));
  }

  @Test
  public void checkFirstBookHasTitle_Author0(){
    Assert.assertThat(books.get(0).getAuthor(), Matchers.equalTo("Author 0"));
  }
}
