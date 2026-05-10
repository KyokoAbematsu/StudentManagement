package raisetech.StudentsManagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import raisetech.StudentsManagement.StudentRepository2;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

  @Mock
  private StudentRepository2 repository;

  @Mock
  private StudentConverter converter;

  private StudentService sut;

  @BeforeEach
  void before() {
    sut = new StudentService(repository, converter);
  }

  @Test
  void 受講生詳細の一覧検索_リポジトリとコンバーターの処理が適切に呼び出せていること() {

    List<Student> studentList = new ArrayList<>();
    List<StudentCourse> studentCourseList = new ArrayList<>();

    when(repository.search()).thenReturn(studentList);
    when(repository.searchStudentCourseList()).thenReturn(studentCourseList);

    sut.searchStudentList();

    verify(repository, times(1)).search();
    verify(repository, times(1)).searchStudentCourseList();
    verify(converter, times(1))
        .convertStudentDetails(studentList, studentCourseList);
  }

  @Test
  void 受講生IDを指定して受講生詳細を検索できること() {

    String studentId = "1";

    Student student = new Student(
        studentId,
        "山田太郎",
        "ヤマダタロウ",
        "タロウ",
        "taro@example.com",
        "東京",
        20,
        "男性",
        "テスト",
        false
    );

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setStudentId(studentId);
    studentCourse.setCourseName("Java");

    List<StudentCourse> studentCourseList = List.of(studentCourse);

    when(repository.searchStudent(studentId)).thenReturn(student);
    when(repository.searchStudentCourse(studentId)).thenReturn(studentCourseList);

    StudentDetail actual = sut.searchStudent(studentId);

    assertEquals(student, actual.getStudent());
    assertEquals(studentCourseList, actual.getStudentCourses());

    verify(repository, times(1)).searchStudent(studentId);
    verify(repository, times(1)).searchStudentCourse(studentId);
  }

  @Test
  void 受講生詳細を登録できること() {

    Student student = new Student(
        "1",
        "山田太郎",
        "ヤマダタロウ",
        "タロウ",
        "taro@example.com",
        "東京",
        20,
        "男性",
        "テスト",
        false
    );

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseName("Java");

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(List.of(studentCourse));

    StudentDetail actual = sut.registerStudent(studentDetail);

    assertEquals(studentDetail, actual);

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生登録時に受講生コース情報の初期値が設定されること() {

    Student student = new Student(
        "1",
        "山田太郎",
        "ヤマダタロウ",
        "タロウ",
        "taro@example.com",
        "東京",
        20,
        "男性",
        "テスト",
        false
    );

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setCourseName("Java");

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(List.of(studentCourse));

    sut.registerStudent(studentDetail);

    assertEquals("1", studentCourse.getStudentId());
    assertNotNull(studentCourse.getCourseStartAt());
    assertNotNull(studentCourse.getCourseEndAt());

    verify(repository, times(1)).registerStudent(student);
    verify(repository, times(1)).registerStudentCourse(studentCourse);
  }

  @Test
  void 受講生詳細を更新できること() {

    Student student = new Student(
        "1",
        "山田太郎",
        "ヤマダタロウ",
        "タロウ",
        "taro@example.com",
        "東京",
        20,
        "男性",
        "更新テスト",
        false
    );

    StudentCourse studentCourse = new StudentCourse();
    studentCourse.setId(1);
    studentCourse.setStudentId("1");
    studentCourse.setCourseName("Java");

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(List.of(studentCourse));

    sut.updateStudent(studentDetail);

    verify(repository, times(1)).updateStudent(student);
    verify(repository, times(1)).updateStudentCourse(studentCourse);
  }
}