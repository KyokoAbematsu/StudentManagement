INSERT INTO students
(id, name, name_kana, nickname, email, region, age, gender, remarks, is_deleted)
VALUES
(11, '阿部松京子', 'アベマツ キョウコ', 'キョウコ', 'kyoko_update@example.com', '東京', 22, 'F', '更新テスト', 0),
(12, 'Suzuki Hanako', 'スズキ ハナコ', 'Hanako', 'hanako@example.com', '京都府', 22, 'F', '', 1),
(13, 'Yamada Taro', 'ヤマダ タロウ', 'Taro', 'taro@example.com', '大阪市', 20, 'M', '', 1),
(15, '佐藤ゆうた', 'サトウユウタ', 'ユウタ', 'yuta@example.com', '大阪', 18, 'M', '', 1),
(16, '山田太郎', 'ヤマダタロウ', 'タロ', 'taro@example.com', '神奈川', 25, 'M', '', 0),
(24, '阿部松京子', 'アベマツ キョウコ', 'キョウコ', 'kyoko@example.com', '福岡', 20, 'F', '新規登録テスト', 0),
(25, '阿部松京子', 'アベマツ キョウコ', 'キョウコ', 'kyoko@example.com', '福岡', 20, 'F', '新規登録テスト', 0),
(26, '阿部松京子', 'アベマツ キョウコ', 'キョウコ', 'kyoko@example.com', '福岡', 20, 'F', 'テスト', 0);

INSERT INTO students_courses
(id, student_id, course_name, start_date, end_date)
VALUES
(1, 11, 'Java基礎', '2026-01-01', '2026-03-31'),
(2, 11, 'Spring Boot', '2026-02-01', '2026-04-30'),
(3, 11, 'Java基礎', '2026-01-01', NULL),
(4, 11, 'DB基礎', '2026-01-03', '2026-01-20'),
(5, 12, 'Spring Boot入門', '2026-01-05', NULL),
(6, 13, '英語プレゼン', '2026-01-02', '2026-01-15'),
(7, 11, 'Web API基礎', '2026-01-10', NULL),
(13, 24, 'Javaコース', '2026-04-16', '2027-04-16');