package edu.ee.models.inheritance2;

import javax.persistence.*;

@Entity
@DiscriminatorValue("circle")
public class Circle extends Shape {

  private int radius;

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }
}
