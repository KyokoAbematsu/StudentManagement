package raisetech.StudentsManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;
import raisetech.StudentsManagement.service.StudentConverter;
import raisetech.StudentsManagement.service.StudentService;

@RestController
public class StudentCourseController {

  private final StudentService service;
  private final StudentConverter converter;

  @Autowired
  public StudentCourseController(StudentService studentservice, StudentConverter converter) {
    this.service = studentservice;
    this.converter = converter;
  }

  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> studentCourses = service.searchStudentCourseList();
    return converter.convertStudentDetails(students, studentCourses);
  }

  @GetMapping("/studentsCourseList")
  public List<StudentCourse> getStudentsCourseList() {
    return service.searchStudentCourseList();
  }

  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable String id) {
    return service.searchStudent(id);
  }

  @PostMapping("/registerStudent")
  public ResponseEntity<String> registerStudent(@RequestBody StudentDetail studentDetail) {
    service.registerStudent(studentDetail);
    return ResponseEntity.ok("登録処理が成功しました。");
  }

  @PostMapping("/updateStudent")
  public ResponseEntity<String> updateStudent(@RequestBody StudentDetail studentDetail) {
    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }
}