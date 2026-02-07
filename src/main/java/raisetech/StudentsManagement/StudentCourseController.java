package raisetech.StudentsManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.service.StudentService;

@RestController
public class StudentCourseController {

  private final StudentService service;

  @Autowired
  public StudentCourseController(StudentService service) {
    this.service = service;
  }

  @GetMapping("/studentList")
  public List<Student> getStudentList() {
    return service.searchStudentList();
  }


  @GetMapping("/studentCourseList")
  public List<StudentCourse> getStudentsCourseList() {
    return service.searchStudentCourseList();
  }


  @GetMapping("/studentList/30s")
  public List<Student> getStudentListIn30s() {
    return service.searchStudentsIn30s();
  }


  @GetMapping("/studentCourseList/java")
  public List<StudentCourse> getJavaCourseList() {
    return service.searchJavaCourses();
  }
}
