package raisetech.StudentsManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.domain.StudentDetail;
import raisetech.StudentsManagement.service.StudentService;

@WebMvcTest(StudentCourseController.class)
class StudentCourseControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private Validator validator;

  @MockitoBean
  private StudentService service;

  @Test
  void 受講生詳細の一覧検索が実行できて空のリストが返ってくること() throws Exception {
    mockMvc.perform(get("/studentList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentList();
  }

  @Test
  void 受講生詳細の受講生IDに数字以外を用いた場合に入力チェックにかかること() {
    Student student = new Student();

    student.setId("テストです。");
    student.setName("江並公次");
    student.setKanaName("エナミコウジ");
    student.setNickName("エナミ");
    student.setEmail("test@example.com");
    student.setRegion("東京");
    student.setAge(22);
    student.setGender("男性");
    student.setRemarks("テスト");
    student.setDeleted(false);

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations).hasSize(1);
    assertThat(violations)
        .extracting(ConstraintViolation::getMessage)
        .containsOnly("数字のみ入力するようにしてください。");
  }
}