package edu.ee.models.inheritance3;

import javax.persistence.*;

@Entity(name = "Shape3")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Shape {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private int x;
  private int y;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}
