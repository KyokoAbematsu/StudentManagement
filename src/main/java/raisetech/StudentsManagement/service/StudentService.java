package raisetech.StudentsManagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raisetech.StudentsManagement.StudentCourseRepository;
import raisetech.StudentsManagement.StudentRepository2;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import org.springframework.transaction.annotation.Transactional;
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
    studentRepository.insertStudent(student);
  }

  @Transactional
  public void registerStudentwithCourses(StudentDetail detail) {
    Student student = detail.getStudent();
    studentRepository.insertStudent(student);
    if (detail.getStudentCourses() ==null) return;

    for (StudentCourse c : detail.getStudentCourses()) {
      if (c == null || c.getCourseName() == null || c.getCourseName().isBlank())
        continue;

      c.setStudentId(student.getId());
      courseRepository.insertStudentCourse(c);
    }
    }

  }

