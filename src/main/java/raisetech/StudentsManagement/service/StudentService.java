package raisetech.StudentsManagement.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentsManagement.StudentRepository2;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;
import raisetech.StudentsManagement.exception.CustomException;

@Service
public class StudentService {

  private StudentRepository2 studentRepository;
  private StudentConverter converter;

  @Autowired
  public StudentService(StudentRepository2 studentRepository, StudentConverter converter) {
    this.studentRepository = studentRepository;
    this.converter = converter;
  }

  /**
   * 受講生詳細の一覧検索です。全件検索を行うので、条件指定は行いません。
   *
   * @return 受講生詳細一覧（全件）
   */
  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = studentRepository.search();
    List<StudentCourse> studentCourseList = studentRepository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  /**
   * 受講生コース情報の一覧検索です。
   *
   * @return 受講生コース情報一覧
   */
  public List<StudentCourse> searchStudentCourseList() {
    return studentRepository.searchStudentCourseList();
  }

  /**
   * 受講生詳細検索です。IDに紐づく受講生情報を取得した後、その受講生に紐づく受講生コース情報を取得して設定します。
   *
   * @param id 受講生ID
   * @return 受講生詳細



  public StudentDetail searchStudent(String id) {
    Student student = studentRepository.searchStudent(id);
    List<StudentCourse> studentCourse =
        studentRepository.searchStudentCourse(String.valueOf(student.getId()));
    return new StudentDetail(student, studentCourse);
  }
  */
  public StudentDetail searchStudent(String id) {
    Student student = studentRepository.searchStudent(id);

    if (student == null) {
      throw new CustomException("受講生が見つかりません。ID: " + id);
    }

    List<StudentCourse> studentCourse =
        studentRepository.searchStudentCourse(String.valueOf(student.getId()));
    return new StudentDetail(student, studentCourse);
  }



  /**
   * 受講生詳細の登録を行います。受講生と受講生コース情報を個別に登録し、
   * 受講生コース情報には受講生情報を紐づける値とコース開始日、コース終了日を設定します。
   *
   * @param studentDetail 受講生詳細
   * @return 登録情報を付与した受講生詳細
   */
  @Transactional
  public StudentDetail registerStudent(StudentDetail studentDetail) {
    Student student = studentDetail.getStudent();

    studentRepository.registerStudent(student);

    if (studentDetail.getStudentCourses() != null) {
      studentDetail.getStudentCourses().forEach(studentCourse -> {
        initStudentCourse(studentCourse, student);
        studentRepository.registerStudentCourse(studentCourse);
      });
    }

    return studentDetail;
  }

  /**
   * 受講生コース情報を登録する際の初期情報を設定する。
   *
   * @param studentCourse 受講生コース情報
   * @param student 受講生
   */
  private static void initStudentCourse(StudentCourse studentCourse, Student student) {
    LocalDateTime now = LocalDateTime.now();

    studentCourse.setStudentId(String.valueOf(student.getId()));
    studentCourse.setCourseStartAt(now);
    studentCourse.setCourseEndAt(now.plusYears(1));
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    studentRepository.updateStudent(studentDetail.getStudent());

    if (studentDetail.getStudentCourses() != null) {
      for (StudentCourse studentCourse : studentDetail.getStudentCourses()) {
        studentRepository.updateStudentCourse(studentCourse);
      }
    }
  }
}