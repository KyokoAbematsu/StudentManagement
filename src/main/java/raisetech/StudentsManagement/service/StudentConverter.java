package raisetech.StudentsManagement.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;

/**
 *
 *受講生詳細を受講生や受講生コース情報、もしくはその逆の返還を行うコンバーターです。
 */

@Component
public class StudentConverter {

  /**
   * 受講生に紐づく受講生コース情報をマッピングする。
   * 受講生コース情報は受講生に対して複数存在するのでループを回して受講生詳細情報を組み立てる。
   *
   * @param students　受講生一覧
   * @param studentCourses　受講生コース情報のリスト
   * @return　受講生詳細情報のリスト
   */
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
