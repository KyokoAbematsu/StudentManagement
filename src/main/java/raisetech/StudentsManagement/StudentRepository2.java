package raisetech.StudentsManagement;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;


@Mapper
public interface StudentRepository2 {

  @Select("SELECT * FROM students")
  List<Student> search();

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchStudentCourse();

  @Insert("""
INSERT INTO students
(name, kana_name, nick_name, email, area, age, sex, remarks, is_deleted)
VALUES
(#{name}, #{kananame}, #{nickname}, #{email}, #{area}, #{age}, #{sex}, #{remarks}, #{isDeleted})
""")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void insertStudent(Student student);



}
