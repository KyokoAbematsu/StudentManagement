package raisetech.StudentsManagement;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;

/**
 * 受講生テーブルと受講生コース情報テーブルと紐づくRepositoryです。
 */

@Mapper
public interface StudentRepository2 {

  /**
   * 受講生の全件検索を行います。
   *
   * @return　受講生一覧（全件）
   */

  @Select("SELECT * FROM students WHERE is_deleted = false")
  List<Student> search();

  /**
   * 受講生の検索を行います。
   *
   * @param id　受講生ID
   * @return　受講生
   */

  @Select("SELECT * FROM students WHERE id = #{id}")
  Student searchStudent(String id);

  /**
   *
   *受講生のコース情報の全件検索を行います。
   *
   * @return　受講生のコース情報（全件）
   */

  @Select("SELECT * FROM students_courses")
  List<StudentCourse> searchStudentCourseList();

  /**
   * 受講生IDに紐づく受講生コース情報を検索します。
   *
   * @param studentId　受講生ID
   * @return　受講生IDに紐づく受講生コース情報
   */

  @Select("SELECT * FROM students_courses WHERE student_id = #{studentId}")
  List<StudentCourse> searchStudentCourse(String studentId);

  @Insert(
      "INSERT INTO students(name, name_kana, nickname, email, region, age, gender, remarks, is_deleted) "
          + "VALUES(#{name}, #{nameKana}, #{nickName}, #{email}, #{region}, #{age}, #{gender}, #{remarks}, false)")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudent(Student student);

  @Insert(
      "INSERT INTO students_courses(student_id, course_name, start_date, end_date) "
          + "VALUES(#{studentId}, #{courseName}, #{courseStartAt}, #{courseEndAt})")
  @Options(useGeneratedKeys = true, keyProperty = "id")
  void registerStudentCourse(StudentCourse studentCourse);

  @Update("UPDATE students SET name = #{name}, name_kana = #{nameKana}, nickname = #{nickName}, email = #{email}, region = #{region}, "
      + "age = #{age}, gender = #{gender}, remarks = #{remarks}, is_deleted = #{deleted} WHERE id = #{id}")
  void updateStudent(Student student);

  @Update("UPDATE students_courses SET course_name = #{courseName} WHERE id = #{id}")
  void updateStudentCourses(StudentCourse studentCourse);
}