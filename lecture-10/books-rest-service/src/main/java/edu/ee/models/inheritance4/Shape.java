package edu.ee.models.inheritance4;

import javax.persistence.*;

@Entity(name = "Shape4")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Shape {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Embedded
  private ShapePoint point;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public ShapePoint getPoint() {
    return point;
  }

  public void setPoint(ShapePoint point) {
    this.point = point;
  }
}
