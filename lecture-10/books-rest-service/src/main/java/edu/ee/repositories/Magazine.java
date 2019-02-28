package edu.ee.repositories;

import javax.persistence.*;

@Entity
public class Magazine {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @Embedded
  @AttributeOverrides(value = {
          @AttributeOverride(name = "name", column = @Column(name = "PUBLISHER_NAME")),
          @AttributeOverride(name = "location", column = @Column(name = "PUBLISHER_LOCATION"))
  })
  private Publisher publisher;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Publisher getPublisher() {
    return publisher;
  }

  public void setPublisher(Publisher publisher) {
    this.publisher = publisher;
  }
}
