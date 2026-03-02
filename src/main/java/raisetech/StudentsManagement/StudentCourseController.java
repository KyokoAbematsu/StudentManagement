package raisetech.StudentsManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.service.StudentConverter;
import raisetech.StudentsManagement.service.StudentService;
import org.springframework.ui.Model;


@Controller
public class StudentCourseController {

  private  StudentService service;
  private StudentConverter converter;


  @Autowired
  public StudentCourseController(StudentService studentservice, StudentConverter converter) {
    this.service =studentservice;
this.converter=converter;
  }

  @GetMapping("/studentList")
  public String studentList(Model model) {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> studentsCourses = service.searchStudentCourseList();

    model.addAttribute("studentList", converter.convertStudentDetails(students, studentsCourses));
    return "studentList";
  }

  @GetMapping("/studentsCourseList")
  public List<StudentCourse> getStudentsCourseList() {
    return service.searchStudentCourseList();

  }
}
