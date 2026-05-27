package raisetech.StudentsManagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;
import raisetech.StudentsManagement.service.StudentConverter;

public class StudentConverterTest {

  private StudentConverter converter = new StudentConverter();

  @Test
  void 受講生と受講生コース情報を受講生詳細に変換できること() {

    Student student = new Student(
        "1",
        "阿部松京子",
        "アベマツキョウコ",
        "キョウコ",
        "test@example.com",
        "福岡",
        20,
        "女性",
        "テスト",
        false
    );

    StudentCourse studentCourse = new StudentCourse(
        1,
        "1",
        "Javaコース",
        null,
        null
    );

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual =
        converter.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual).hasSize(1);
    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourses()).isEqualTo(studentCourseList);
  }

  @Test
  void 対象の受講生IDが存在しない場合は受講生コース情報が紐づかないこと() {

    Student student = new Student(
        "1",
        "阿部松京子",
        "アベマツキョウコ",
        "キョウコ",
        "test@example.com",
        "福岡",
        20,
        "女性",
        "テスト",
        false
    );

    StudentCourse studentCourse = new StudentCourse(
        1,
        "999",
        "Javaコース",
        null,
        null
    );

    List<Student> studentList = List.of(student);
    List<StudentCourse> studentCourseList = List.of(studentCourse);

    List<StudentDetail> actual =
        converter.convertStudentDetails(studentList, studentCourseList);

    assertThat(actual).hasSize(1);
    assertThat(actual.get(0).getStudent()).isEqualTo(student);
    assertThat(actual.get(0).getStudentCourses()).isEmpty();
  }
}