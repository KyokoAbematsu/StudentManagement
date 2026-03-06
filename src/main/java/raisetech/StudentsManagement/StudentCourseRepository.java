package raisetech.StudentsManagement;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;

@Mapper

public interface StudentCourseRepository {

  @Select("""
      SELECT
      id,
      student_id AS studentId,
      course_name AS courseName,
      start_date AS startDate,
      end_date AS endDate
      FROM students_courses
      """)
  List<StudentCourse> findAll();

  @Insert("""
      INSERT INTO students_courses(student_id, course_name)
      VALUES(#{studentId}, #{courseName})
      """)
  void insertStudentCourse(StudentCourse course);
}

