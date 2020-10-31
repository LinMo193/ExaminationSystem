package com.join.service.teacher;

import com.join.entity.StudentExamInfo;

import java.util.List;

public interface TeacherService {
    /**
     * 查看所有学生考试信息
     * @return
     */
    public List<StudentExamInfo> searchStudentExamInfoService();

    /**
     * 查询某一学生的成绩
     * @param studentExamInfo
     * @return
     */
    public StudentExamInfo searchOneStudentService(StudentExamInfo studentExamInfo);
}
