package edu.ee.booksviewservice.dto;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.ObjectContent;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

@RunWith(SpringRunner.class)
@JsonTest
public class BookDTOJsonTests {

  //@Autowired
  private JacksonTester<BookDTO> json;

  @Test
  public void simpleDeserialization() throws IOException {
    String jsonText = "{\"id\": 10}";
    ObjectContent<BookDTO> parse = json.parse(jsonText);
    Assert.assertThat(parse.getObject().getId(), Matchers.is(10));
  }

  @Test
  public void simpleDeserializationFromFile() throws IOException {
    ObjectContent<BookDTO> parse = json.read(new File("book.json"));
    Assert.assertThat(parse.getObject().getId(), Matchers.is(10));
  }
}
