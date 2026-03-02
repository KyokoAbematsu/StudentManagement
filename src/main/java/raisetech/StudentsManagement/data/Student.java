package raisetech.StudentsManagement.data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private String id;
  private String name;
  private String kananame;
  private String nickname;
  private String email;
  private String area;
  private int age;
  private String sex;
  private String remarks;
  private boolean isDeleted;
}