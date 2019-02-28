package edu.ee.models.inheritance4;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity(name = "Circle4")
@PrimaryKeyJoinColumn
public class Circle extends Shape {

  private int radius;

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }
}
