package raisetech.StudentsManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentsManagement.StudentRepository2;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;

@Service
public class StudentService {

  private StudentRepository2 repository;

  @Autowired
  public StudentService(StudentRepository2 repository) {
    this.repository = repository;
  }


  public List<Student> searchStudentList() {
    return repository.search();
  }


  public List<StudentCourse> searchStudentCourseList() {
    return repository.searchStudentCourse();
  }
}

