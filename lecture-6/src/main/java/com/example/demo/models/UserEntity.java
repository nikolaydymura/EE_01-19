package com.example.demo.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @OneToMany(mappedBy = "owner")
  private Set<BookEntity> favouriteBook;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Set<BookEntity> getFavouriteBook() {
    return favouriteBook;
  }

  public void setFavouriteBook(Set<BookEntity> favouriteBook) {
    this.favouriteBook = favouriteBook;
  }

  public void addBook(BookEntity bookEntity){
    bookEntity.setOwner(this);
    this.favouriteBook.add(bookEntity);
  }
}
