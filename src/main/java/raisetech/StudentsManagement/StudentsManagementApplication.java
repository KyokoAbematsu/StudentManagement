package raisetech.StudentsManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import raisetech.StudentsManagement.data.Student;
import raisetech.StudentsManagement.data.StudentCourse;

@OpenAPIDefinition(info = @Info(title = "受講生管理システム"))
@SpringBootApplication
public class StudentsManagementApplication<name> {

  @Autowired
  private StudentRepository2 repository;

  public static void main(String[] args) {
    SpringApplication.run(StudentsManagementApplication.class, args);

  }

  }

