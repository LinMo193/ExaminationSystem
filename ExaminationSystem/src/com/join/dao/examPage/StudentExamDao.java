package com.join.dao.examPage;

import com.join.entity.*;

import javax.servlet.ServletResponse;
import java.util.List;

public interface StudentExamDao {
    /**
     * 学生考试时先获取该试卷的信息
     * @param examBasicInfo
     * @return
     */
    public ExamBasicInfo getExamInfo(ExamBasicInfo examBasicInfo);

    /**
     * 返回试卷中所有的试题
     */
    public List<ExamAboutQuestion> getQuestionInfo(ExamBasicInfo examBasicInfo);

    /**
     * 学生提交试卷后将答题信息存储到数据库中
     */
    public boolean completedExam(List<ExamAboutQuestion> list, Student student,String courseName);

    /**
     * 学生提交后计算得分（单选题，填空题）
     */
    public boolean submitExam(List<ExamAboutQuestion> list, String courseName,Student student);

    /**
     * 查看学生的答题情况
     * @param student
     * @param studentExamInfo
     * @return
     */
    public List<CheckStudentExamInfo> checkStudentExamInfo(Student student, StudentExamInfo studentExamInfo);
}
