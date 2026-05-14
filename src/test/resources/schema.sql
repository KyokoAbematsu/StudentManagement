CREATE TABLE IF NOT EXISTS students
 (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50),
  name_kana VARCHAR(100),
  nick_name VARCHAR(100),
  email VARCHAR(255),
  region VARCHAR(100),
  age INT,
  gender VARCHAR(50),
  remarks VARCHAR(255),
  is_deleted BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE IF NOT EXISTS students_courses (
  id INT PRIMARY KEY AUTO_INCREMENT,
  student_id INT NOT NULL,
  course_name VARCHAR(100) NOT NULL,
  start_date TIMESTAMP,
  end_date TIMESTAMP
);