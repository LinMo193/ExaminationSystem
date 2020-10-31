package com.join.sql.teacher;

public class TeacherSql {
    /**
     * 查询所有学生的考试成绩
     */
    public static String searchStudentExamInfo="select * from studentexamscore";

    /**
     * 查询某一学生的考试成绩
     */
    public static String searchOneStudent="select sno,examName,actualScore from studentexamscore where username=?";
}
