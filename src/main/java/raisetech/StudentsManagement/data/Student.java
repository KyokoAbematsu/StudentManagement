package raisetech.StudentsManagement.data;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String id;

  @NotBlank(message = "名前は必須です")
  private String name;

  private String nameKana;
  private String nickName;
  private String email;
  private String region;
  private int age;
  private String gender;
  private String remarks;
  private boolean deleted;
}