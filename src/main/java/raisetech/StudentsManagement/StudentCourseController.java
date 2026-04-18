package raisetech.StudentsManagement;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;
import raisetech.StudentsManagement.service.StudentService;

/**
 * 受講生の検索や登録、更新を行うREST APIとして受け付けるControllerです。
 */
@RestController
public class StudentCourseController {

  private final StudentService service;

  @Autowired
  public StudentCourseController(StudentService studentService) {
    this.service = studentService;
  }

  /**
   * 受講生一覧検索です。
   * 全件検索を行うので、条件指定は行いません。
   *
   * @return 受講生一覧（全件）
   */
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() {
    return service.searchStudentList();
  }

  /**
   * 受講生コース情報一覧検索です。
   *
   * @return 受講生コース情報一覧
   */
  @GetMapping("/studentsCourseList")
  public List<StudentCourse> getStudentsCourseList() {
    return service.searchStudentCourseList();
  }

  /**
   * 受講生検索です。
   * IDに紐づく任意の受講生の情報を取得します。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
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