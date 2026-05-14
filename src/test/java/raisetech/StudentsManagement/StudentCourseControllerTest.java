package raisetech.StudentsManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
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
  void 旧受講生一覧検索APIを実行すると400が返ること() throws Exception {

    mockMvc.perform(get("/studentList"))
        .andExpect(status().isBadRequest())
        .andExpect(content().string(
            "現在このAPIは利用できません。URLは「studentList」ではなく「students」を利用してください。"));
  }

  @Test
  void 受講生コース一覧検索が実行できること() throws Exception {

    when(service.searchStudentCourseList()).thenReturn(List.of());

    mockMvc.perform(get("/studentsCourseList"))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudentCourseList();
  }

  @Test
  void 受講生詳細の検索が実行できること() throws Exception {

    String id = "999";

    when(service.searchStudent(id)).thenReturn(new StudentDetail());

    mockMvc.perform(get("/student/{id}", id))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(id);
  }

  @Test
  void 受講生詳細の受講生IDに数字以外を用いた場合に入力チェックにかかること() {

    Student student = new Student(
        "テストです。",
        "江並公次",
        "エナミコウジ",
        "エナミ",
        "test@example.com",
        "東京",
        22,
        "男性",
        "テスト",
        false
    );

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations).hasSize(1);

    assertThat(violations)
        .extracting(ConstraintViolation::getMessage)
        .containsOnly("数字のみ入力するようにしてください。");
  }

  @Test
  void 受講生詳細の登録が実行できること() throws Exception {

    when(service.registerStudent(any())).thenReturn(new StudentDetail());

    mockMvc.perform(post("/registerStudent")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "student": {
                  "id": "1",
                    "name": "阿部松京子",
                    "nameKana": "アベマツキョウコ",
                    "nickName": "キョウコ",
                    "email": "test@example.com",
                    "region": "福岡",
                    "age": 21,
                    "gender": "女性",
                    "remarks": "テスト",
                    "deleted": false
                  },
                  "studentCourses": [
                    {
                      "courseName": "Javaコース"
                    }
                  ]
                }
                """))
        .andExpect(status().isOk());

    verify(service, times(1)).registerStudent(any());
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力したときに入力チェックに異常が発生しないこと() {

    Student student = new Student(
        "1",
        "阿部松京子",
        "アベマツキョウコ",
        "キョウコ",
        "test@example.com",
        "福岡",
        21,
        "女性",
        "テスト",
        false
    );

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations).isEmpty();
  }
}