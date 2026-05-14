package raisetech.StudentsManagement;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
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
  void コース一覧検索ができること() {

    List<StudentCourse> courses = repository.searchStudentCourseList();

    assertThat(courses).isNotEmpty();
  }

  @Test
  void 受講生コース検索ができること() {

    List<StudentCourse> courses = repository.searchStudentCourse("11");

    assertThat(courses).hasSize(5);
    assertThat(courses.get(0).getCourseName()).isEqualTo("Java基礎");
  }

  @Test
  void 受講生登録ができること() {

    Student student = new Student(
        null,
        "テスト太郎",
        "テストタロウ",
        "テスト",
        "test@example.com",
        "福岡",
        20,
        "M",
        "",
        false
    );

    repository.registerStudent(student);

    List<Student> students = repository.search();

    assertThat(students)
        .extracting(Student::getName)
        .contains("テスト太郎");
  }
  @Test
  void コース登録ができること() {

    StudentCourse course = new StudentCourse();

    course.setStudentId("11");
    course.setCourseName("JUnitテスト");
    course.setCourseStartAt(LocalDateTime.now());
    course.setCourseEndAt(LocalDateTime.now().plusMonths(3));

    repository.registerStudentCourse(course);

    List<StudentCourse> courses = repository.searchStudentCourse("11");

    assertThat(courses)
        .extracting(StudentCourse::getCourseName)
        .contains("JUnitテスト");
  }
  @Test
  void 受講生更新ができること() {

    Student student = repository.searchStudent("11");

    student.setName("更新後");

    repository.updateStudent(student);

    Student updatedStudent = repository.searchStudent("11");

    assertThat(updatedStudent.getName()).isEqualTo("更新後");
  }
  @Test
  void コース更新ができること() {

    List<StudentCourse> courses = repository.searchStudentCourse("11");

    StudentCourse course = courses.get(0);
    course.setCourseName("更新後コース");

    repository.updateStudentCourse(course);

    List<StudentCourse> updatedCourses = repository.searchStudentCourse("11");

    assertThat(updatedCourses)
        .extracting(StudentCourse::getCourseName)
        .contains("更新後コース");
  }
}