package raisetech.StudentsManagement.data;

import java.time.LocalDateTime;
import java.util.Objects;

public class StudentCourse {

  private int id;
  private String studentId;
  private String courseName;
  private LocalDateTime courseStartAt;
  private LocalDateTime courseEndAt;

  public StudentCourse() {
  }

  public StudentCourse(
      int id,
      String studentId,
      String courseName,
      LocalDateTime courseStartAt,
      LocalDateTime courseEndAt) {

    this.id = id;
    this.studentId = studentId;
    this.courseName = courseName;
    this.courseStartAt = courseStartAt;
    this.courseEndAt = courseEndAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStudentId() {
    return studentId;
  }

  public void setStudentId(String studentId) {
    this.studentId = studentId;
  }

  public String getCourseName() {
    return courseName;
  }

  public void setCourseName(String courseName) {
    this.courseName = courseName;
  }

  public LocalDateTime getCourseStartAt() {
    return courseStartAt;
  }

  public void setCourseStartAt(LocalDateTime courseStartAt) {
    this.courseStartAt = courseStartAt;
  }

  public LocalDateTime getCourseEndAt() {
    return courseEndAt;
  }

  public void setCourseEndAt(LocalDateTime courseEndAt) {
    this.courseEndAt = courseEndAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof StudentCourse that)) {
      return false;
    }
    return id == that.id
        && Objects.equals(studentId, that.studentId)
        && Objects.equals(courseName, that.courseName)
        && Objects.equals(courseStartAt, that.courseStartAt)
        && Objects.equals(courseEndAt, that.courseEndAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        studentId,
        courseName,
        courseStartAt,
        courseEndAt
    );
  }
}