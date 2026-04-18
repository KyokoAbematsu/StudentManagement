package raisetech.StudentsManagement.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetail {

  private Student student;
  private List<StudentCourse> studentCourses;
}

