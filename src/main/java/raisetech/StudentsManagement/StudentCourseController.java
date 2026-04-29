package raisetech.StudentsManagement;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentsManagement.data.StudentCourse;
import raisetech.StudentsManagement.domain.StudentDetail;
import raisetech.StudentsManagement.exception.TestException;
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
   * 受講生詳細の一覧検索です。
   * 現在は旧APIとして扱い、利用できないようにしています。
   *
   * @return エラーメッセージ
   */
  @Operation(
      summary = "旧受講生一覧検索",
      description = "現在このAPIは利用できません。受講生一覧の取得には /students を利用してください。"
  )
  @GetMapping("/studentList")
  public List<StudentDetail> getStudentList() throws TestException {
    throw new TestException("現在このAPIは利用できません。URLは「studentList」ではなく「students」を利用してください。");
  }

  /**
   * 受講生コース情報一覧検索です。
   *
   * @return 受講生コース情報一覧
   */
  @Operation(
      summary = "受講生コース一覧検索",
      description = "受講生コース情報の一覧を取得します。"
  )
  @GetMapping("/studentsCourseList")
  public List<StudentCourse> getStudentsCourseList() {
    return service.searchStudentCourseList();
  }

  /**
   * 受講生詳細の検索です。
   * IDに紐づく任意の受講生の情報を取得します。
   *
   * @param id 受講生ID
   * @return 受講生詳細
   */
  @Operation(
      summary = "受講生詳細検索",
      description = "指定されたIDに紐づく受講生詳細を取得します。"
  )
  @GetMapping("/student/{id}")
  public StudentDetail getStudent(@PathVariable @Size(min = 1, max = 3) String id) {
    return service.searchStudent(id);
  }

  /**
   * 受講生詳細の登録を行います。
   *
   * @param studentDetail 受講生詳細
   * @return 登録された受講生詳細
   */
  @Operation(
      summary = "受講生登録",
      description = "受講生詳細情報を登録します。入力チェックに失敗した場合はエラー内容を返します。"
  )
  @PostMapping("/registerStudent")
  public ResponseEntity<?> registerStudent(
      @Valid @RequestBody StudentDetail studentDetail,
      BindingResult result) {

    if (result.hasErrors()) {
      return ResponseEntity.badRequest().body(result.getAllErrors());
    }

    StudentDetail responseStudentDetail = service.registerStudent(studentDetail);
    return ResponseEntity.ok(responseStudentDetail);
  }

  /**
   * 受講生詳細の更新を行います。
   * キャンセルフラグの更新もここで行います（論理削除）。
   *
   * @param studentDetail 受講生詳細
   * @return 実行結果
   */
  @Operation(
      summary = "受講生更新",
      description = "受講生詳細情報を更新します。論理削除フラグの更新も行います。入力チェックに失敗した場合はエラー内容を返します。"
  )
  @PutMapping("/updateStudent")
  public ResponseEntity<?> updateStudent(
      @Valid @RequestBody StudentDetail studentDetail,
      BindingResult result) {

    if (result.hasErrors()) {
      return ResponseEntity.badRequest().body(result.getAllErrors());
    }

    service.updateStudent(studentDetail);
    return ResponseEntity.ok("更新処理が成功しました。");
  }

  @ExceptionHandler(TestException.class)
  public ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }
}