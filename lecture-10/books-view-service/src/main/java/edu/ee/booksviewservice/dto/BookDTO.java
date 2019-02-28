package edu.ee.booksviewservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookDTO {
  @JsonProperty("i")
  private int id;
  @JsonProperty("tit")
  private String title;
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
