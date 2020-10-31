package com.join.dao.teacher;

import com.join.entity.StudentExamInfo;

import java.util.List;

public interface TeacherDao {
    /**
     * 查看所有学生考试信息
     * @return
     */
    public List<StudentExamInfo> searchStudentExamInfo();

    /**
     * 查询某一学生的成绩
     * @param studentExamInfo
     * @return
     */
    public StudentExamInfo searchOneStudent(StudentExamInfo studentExamInfo);
}
