package raisetech.StudentsManagement.data;

public class Student {

  private String id;
  private String name;
  private String nameKana;
  private String nickName;
  private String email;
  private String region;
  private int age;
  private String gender;
  private String remarks;
  private boolean deleted;

  public Student() {
  }

  public Student(
      String id,
      String name,
      String nameKana,
      String nickName,
      String email,
      String region,
      int age,
      String gender,
      String remarks,
      boolean deleted) {

    this.id = id;
    this.name = name;
    this.nameKana = nameKana;
    this.nickName = nickName;
    this.email = email;
    this.region = region;
    this.age = age;
    this.gender = gender;
    this.remarks = remarks;
    this.deleted = deleted;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameKana() {
    return nameKana;
  }

  public void setNameKana(String nameKana) {
    this.nameKana = nameKana;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public boolean isDeleted() {
    return deleted;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }
}