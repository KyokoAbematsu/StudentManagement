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
    List<StudentCourse> studentCourses =
        studentRepository.searchStudentCourse(String.valueOf(student.getId()));

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(studentCourses);

    return studentDetail;
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    studentRepository.registerStudent(studentDetail.getStudent());

    if (studentDetail.getStudentCourses() != null) {
      for (StudentCourse studentCourse : studentDetail.getStudentCourses()) {
        studentCourse.setStudentId(String.valueOf(studentDetail.getStudent().getId()));
        studentCourse.setCourseStartAt(LocalDateTime.now());
        studentCourse.setCourseEndAt(LocalDateTime.now().plusYears(1));
        studentRepository.registerStudentCourse(studentCourse);
      }
    }
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    studentRepository.updateStudent(studentDetail.getStudent());

    if (studentDetail.getStudentCourses() != null) {
      for (StudentCourse studentCourse : studentDetail.getStudentCourses()) {
        studentCourse.setStudentId(String.valueOf(studentDetail.getStudent().getId()));
        studentRepository.updateStudentCourses(studentCourse);
      }
    }
  }
}