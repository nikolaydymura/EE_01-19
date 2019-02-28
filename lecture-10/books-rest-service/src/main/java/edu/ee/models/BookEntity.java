package edu.ee.models;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity(name = "Book")
@Immutable
public class BookEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String title;

  private String author;

  @Formula("EE_TITLE || ' ' || EE_AUTHOR")
  private String prettyTitle;

  @ManyToOne()
  @JoinColumn(name = "OWNER_ID")
  private UserEntity owner;

  @Lob
  private byte[] image;

  public UserEntity getOwner() {
    return owner;
  }

  public void setOwner(UserEntity owner) {
    this.owner = owner;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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
