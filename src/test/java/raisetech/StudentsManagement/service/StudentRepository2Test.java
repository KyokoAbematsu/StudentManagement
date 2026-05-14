package raisetech.StudentsManagement.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import raisetech.StudentsManagement.StudentRepository2;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;

@MybatisTest
class StudentRepository2Test {

  @Autowired
  private StudentRepository2 repository;

  @Test
  void 受講生一覧検索ができること() {

    List<Student> students = repository.search();

    assertThat(students).isNotEmpty();
    assertThat(students.size()).isGreaterThan(0);
  }

  @Test
  void 受講生ID検索ができること() {

    Student student = repository.searchStudent("11");

    assertThat(student).isNotNull();
    assertThat(student.getName()).isEqualTo("阿部松京子");
    assertThat(student.getRegion()).isEqualTo("東京");
  }

  @Test
  void 受講生コース検索ができること() {

    List<StudentCourse> courses =
        repository.searchStudentCourse("11");

    assertThat(courses).hasSize(4);
    assertThat(courses.get(0).getCourseName())
        .isEqualTo("Java基礎");
  }
}