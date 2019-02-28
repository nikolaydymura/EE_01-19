package edu.ee.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class BookDTO {
  @JsonProperty("i")
  private int id;
  @Size(min = 6, max = 10)
  @JsonProperty("tit")
  private String title;
  @Size(min = 6, max = 10)
  @JsonProperty("auth")
  private String author;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}
