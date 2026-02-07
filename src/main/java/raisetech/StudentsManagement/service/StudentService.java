package raisetech.StudentsManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentsManagement.StudentRepository2;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;

@Service
public class StudentService {

  private final StudentRepository2 repository;

  @Autowired
  public StudentService(StudentRepository2 repository) {
    this.repository = repository;
  }


  public List<Student> searchStudentList() {
    return repository.search();
  }


  public List<StudentCourse> searchStudentCourseList() {
    return repository.searchStudentCourse(); // ←ここはRepositoryに合わせて調整
  }


  public List<Student> searchStudentsIn30s() {
    return repository.search().stream()
        .filter(s -> s.getAge() >= 30 && s.getAge() <= 39)
        .toList();
  }

  public List<StudentCourse> searchJavaCourses() {
    return repository.searchStudentCourse().stream()
        .filter(c -> "Java".equals(c.getCourseName()))
        .toList();
  }
}
