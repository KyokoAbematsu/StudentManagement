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

  public List<StudentDetail> searchStudentList() {
    List<Student> studentList = studentRepository.search();
    List<StudentCourse> studentCourseList = studentRepository.searchStudentCourseList();
    return converter.convertStudentDetails(studentList, studentCourseList);
  }

  public List<StudentCourse> searchStudentCourseList() {
    return studentRepository.searchStudentCourseList();
  }

  public StudentDetail searchStudent(String id) {
    Student student = studentRepository.searchStudent(id);

    if (student == null) {
      throw new CustomException("受講生が見つかりません。ID: " + id);
    }

    List<StudentCourse> studentCourse =
        studentRepository.searchStudentCourse(String.valueOf(student.getId()));

    return new StudentDetail(student, studentCourse);
  }

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