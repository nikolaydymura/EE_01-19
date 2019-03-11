package edu.ee.books_rest_api;

import org.springframework.hateoas.ResourceSupport;

public class BookDTO extends ResourceSupport {
  private Long uid;
  private String title;

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
