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
  private StudentCourseRepository courseRepository;

  @Autowired
  public StudentService(StudentRepository2 studentRepository, StudentCourseRepository courseRepository) {
    this.studentRepository = studentRepository;
    this.courseRepository = courseRepository;
  }

  public List<Student> searchStudentList() {
    return studentRepository.search();
  }

  public List<StudentCourse> searchStudentCourseList() {
    return courseRepository.findAll();
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

  public StudentDetail searchStudent(String id) {
    Student student = studentRepository.searchStudentById(id);
    List<StudentCourse> studentCourses = studentRepository.searchStudentCourseByStudentId(id);

    StudentDetail studentDetail = new StudentDetail();
    studentDetail.setStudent(student);
    studentDetail.setStudentCourses(studentCourses);

    return studentDetail;
  }

  @Transactional
  public void updateStudent(StudentDetail studentDetail) {
    studentRepository.updateStudent(studentDetail.getStudent());
  }

}