package raisetech.StudentsManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;

@Component
public class StudentConverter {

  public List<StudentDetail> convertStudentDetails(
      List<Student> students,
      List<StudentCourse> studentCourses) {

    List<StudentDetail> studentDetails = new ArrayList<>();

    for (Student student : students) {
      StudentDetail detail = new StudentDetail();
      detail.setStudent(student);

      List<StudentCourse> coursesOfStudent = new ArrayList<>();
      for (StudentCourse course : studentCourses) {
        if (student.getId().equals(course.getStudentId())) {
          coursesOfStudent.add(course);
        }
      }

      detail.setStudentCourses(coursesOfStudent);
      studentDetails.add(detail);
    }

    return studentDetails;
  }
}
