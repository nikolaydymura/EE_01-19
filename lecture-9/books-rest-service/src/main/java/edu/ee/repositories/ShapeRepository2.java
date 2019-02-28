package edu.ee.repositories;

import edu.ee.models.inheritance2.Shape;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShapeRepository2 extends JpaRepository<Shape, Long> {

}
