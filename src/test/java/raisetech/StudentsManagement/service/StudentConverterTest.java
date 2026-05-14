package raisetech.StudentsManagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;

public class StudentConverterTest {

  private StudentConverter converter = new StudentConverter();

  @Test
  void 受講生と受講生コース情報を受講生詳細に変換できること() {
    Student student = new Student();
    student.setId("1");
    student.setName("阿部松京子");

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("Javaコース");

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual =
        converter.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual).hasSize(1);
    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourses()).isEqualTo(studentCourseList);
  }
}