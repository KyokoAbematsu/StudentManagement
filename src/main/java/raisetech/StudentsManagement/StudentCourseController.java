package raisetech.StudentsManagement;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentManagement.domain.StudentDetail;
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
  public List<StudentDetail> getStudentList() {
    List<Student> students = service.searchStudentList();
    List<StudentCourse> studentCourses = service.searchStudentCourseList();

    List<StudentDetail> studentDetails = new ArrayList<>();

    for (Student student : students) {
      StudentDetail studentDetail = new StudentDetail();
      studentDetail.setStudent(student);

      List<StudentCourse> convertStudentCourse = new ArrayList<>();
      for (StudentCourse studentCourse : studentCourses) {
        if (student.getId().equals(studentCourse.getStudentId())) {
          convertStudentCourse.add(studentCourse);
        }
      }

      studentDetail.setStudentCourses(convertStudentCourse);
      studentDetails.add(studentDetail);
    }

    return studentDetails;
  }

  @GetMapping("/studentCourseList")
  public List<StudentCourse> getStudentsCourseList() {
    return service.searchStudentCourseList();
  }
}
