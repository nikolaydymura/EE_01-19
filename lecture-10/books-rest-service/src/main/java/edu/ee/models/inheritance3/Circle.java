package edu.ee.models.inheritance3;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "Circle3")
public class Circle extends Shape {

  private int radius;

  public int getRadius() {
    return radius;
  }

  public void setRadius(int radius) {
    this.radius = radius;
  }
}
