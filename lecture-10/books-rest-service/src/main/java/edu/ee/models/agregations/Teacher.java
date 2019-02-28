package edu.ee.models.agregations;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
public class Teacher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ManyToMany
  @JoinTable(name = "students_teachers",
          joinColumns =
                  {@JoinColumn(name = "student_id")},
          inverseJoinColumns =
                  {@JoinColumn(name = "teacher_id")})
  private Set<Student> students = new TreeSet<>();

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
