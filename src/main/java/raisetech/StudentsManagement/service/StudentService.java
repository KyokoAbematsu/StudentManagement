package raisetech.StudentsManagement.service;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raisetech.StudentsManagement.StudentCourseRepository;
import raisetech.StudentsManagement.StudentRepository2;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;

@Service
public class StudentService {

  private StudentRepository2 studentRepository;

  @Autowired
  public StudentService(StudentRepository2 studentRepository,
      StudentCourseRepository courseRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> searchStudentList() {
    return studentRepository.search();
  }

  public List<StudentCourse> searchStudentCourseList() {
    return studentRepository.searchStudentCourseList();
  }

  public StudentDetail searchStudent(String id) {
    Student student = studentRepository.searchStudent(id);
    List<StudentCourse> studentCourses = studentRepository.searchStudentCourse(student.getId());
    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(studentCourses);
    return studentDetail;
  }

  public void registerStudent(Student student) {
    studentRepository.registerStudent(student);
  }

  @Transactional
  public void registerStudent(StudentDetail studentDetail) {
    studentRepository.registerStudent(studentDetail.getStudent());
    for (StudentCourse studentCourse : studentDetail.getStudentCourses()) {
      studentCourse.setStudentId(studentDetail.getStudent().getId());
      studentCourse.setCourseStartAt(LocalDateTime.now());
      studentCourse.setCourseEndAt(LocalDateTime.now().plusYears(1));
      studentRepository.registerStudentCourse(studentCourse);
    }
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    studentRepository.updateStudent(studentDetail.getStudent());

    if (studentDetail.getStudentCourses() != null) {
      for (StudentCourse studentCourse : studentDetail.getStudentCourses()) {
        studentCourse.setStudentId(studentDetail.getStudent().getId());
        studentRepository.updateStudentCourses(studentCourse);
      }
    }
  }
}