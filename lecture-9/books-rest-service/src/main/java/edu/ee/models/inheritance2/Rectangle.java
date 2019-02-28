package edu.ee.models.inheritance2;

import javax.persistence.*;

@Entity
@DiscriminatorValue("rectangle")
public class Rectangle extends Shape {
  private int width;
  private int height;

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }
}
