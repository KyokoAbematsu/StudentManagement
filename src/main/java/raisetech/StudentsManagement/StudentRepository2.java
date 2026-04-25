package raisetech.StudentsManagement;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;

@Mapper
public interface StudentRepository2 {

  List<Student> search();

  Student searchStudent(String id);

  List<StudentCourse> searchStudentCourseList();

  List<StudentCourse> searchStudentCourse(String studentId);

  void registerStudent(Student student);

  void registerStudentCourse(StudentCourse studentCourse);

  void updateStudent(Student student);

  void updateStudentCourse(StudentCourse studentCourse);
}