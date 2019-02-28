package edu.ee.models.agregations;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "students_teachers")
public class ExtendedTeacherStudent {

  @EmbeddedId
  private TeacherStudentKey id;

  @OneToOne
  @JoinColumn(name = "student_id")
  private Student student;

  @OneToOne
  @JoinColumn(name = "teacher_id")
  private Teacher teacher;

  @Embeddable
  public static class TeacherStudentKey implements Serializable {

    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "teacher_id")
    private Integer teacherId;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      TeacherStudentKey that = (TeacherStudentKey) o;
      return Objects.equals(studentId, that.studentId) &&
              Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(studentId, teacherId);
    }
  }
}


