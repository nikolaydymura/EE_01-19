package edu.ee.models.inheritance4;

import javax.persistence.Embeddable;

@Embeddable
public class ShapePoint {
  private int x;
  private int y;

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
