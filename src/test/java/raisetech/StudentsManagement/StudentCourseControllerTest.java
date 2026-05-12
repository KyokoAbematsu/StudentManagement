package raisetech.StudentsManagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester.MockMvcRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.domain.StudentDetail;
import raisetech.StudentsManagement.service.StudentService;


@Schema(description = "受講生")
@Getter
@Setter
public class student {

@NotBlank
@Pattern(regexp = "^\\d+$", message = "数字のみ入力するようにしてください。")
private String id;

@NotBlank
private  String name;

@NotBlank
  private String kanaName;

@NotBlank
private String nickname;

@NotBlank
@Email

class StudentCourseControllerTest {

  @WebMvcTest(StudentCourseController.class)
  class StudentCourseControllerTest{

    @Autowired
    private MockMvc mockMvc;

  @MockitoBean
  private StudentService service;

  @Test
  void 受講生詳細の一覧検索が実行できてからのリストが返ってくること()  throws Exception {
    List.of {
      mockMvc.perform(get("/studentList"))
          .andExpect(status().isOk());

      verify(service, times(1)).searchStudentList();
    }

    @Test
    void 受講生詳細の受講生でIDに数字以外を用いた場合に入力チェックにかかること() {
      Student student = new Student();
      student.setId("テストです。");
student.getName("江並公次");
student.setKanaName("エナミコウジ");
studentsetNickName("エナミ");
student.setEmail("test@example.com");
student.setSex("男性");
      Set<ConstraintViolation<Student>> violations = validator.validate(student);

      assertThat(violations.size()).isEqualTo(1);
      assertThat(violations).extracting("message")
          .containsOnly("数字のみ入力するようにしてください。");
  }
