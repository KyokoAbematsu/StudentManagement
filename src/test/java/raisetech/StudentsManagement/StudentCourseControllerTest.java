package raisetech.StudentsManagement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import raisetech.StudentsManagement.data.Student;
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
  void 受講生詳細の検索が実行できて空で返ってくること() throws Exception {
    String id = "999";

    mockMvc.perform(get("/student/{id}", id))
        .andExpect(status().isOk());

    verify(service, times(1)).searchStudent(id);
  }

  @Test
  void 受講生詳細の受講生IDに数字以外を用いた場合に入力チェックにかかること() {
    Student student = new Student();

    student.setId("テストです。");
    student.setName("江並公次");
    student.setNameKana("エナミコウジ");
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

  @Test
  void 受講生詳細の登録が実行できて空で返ってくること() throws Exception {
    mockMvc.perform(post("/registerStudent")
            .contentType(MediaType.APPLICATION_JSON)
            .content("""
                {
                  "student": {
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
  void 受講生詳細の例外APIが実行できてステータスが400で返ってくること() throws Exception {
    mockMvc.perform(get("/exception"))
        .andExpect(status().is4xxClientError())
        .andExpect(content().string("このAPIは現在利用できません。古いURLとなっています。"));
  }

  @Test
  void 受講生詳細の受講生で適切な値を入力したときに入力チェックに異常が発生しないこと() {
    Student student = new Student();

    student.setId("1");
    student.setName("阿部松京子");
    student.setNameKana("アベマツキョウコ");
    student.setNickName("キョウコ");
    student.setEmail("test@example.com");
    student.setRegion("福岡");
    student.setAge(21);
    student.setGender("女性");
    student.setRemarks("テスト");
    student.setDeleted(false);

    Set<ConstraintViolation<Student>> violations = validator.validate(student);

    assertThat(violations).isEmpty();
  }
}