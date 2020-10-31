package com.join.service.examPage;

import com.join.entity.*;
import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.util.List;

public interface StudentExamService {
    /**
     * 学生考试时先获取该试卷的信息
     * @param examBasicInfo
     * @return
     */
    public ExamBasicInfo getExamInfoService(ExamBasicInfo examBasicInfo);

    /**
     * 返回试卷中所有的试题
     * @param examBasicInfo
     * @return
     */
    public List<ExamAboutQuestion> getQuestionInfoService(ExamBasicInfo examBasicInfo);

    /**
     * 学生提交试卷后将答题信息存储到数据库中
     */
    public boolean completedExamService(List<ExamAboutQuestion> list, Student student,String courseName);

    /**
     * 学生提交后计算得分（单选题，填空题）
     */
    public boolean submitExamService(List<ExamAboutQuestion> list, String courseName,Student student);

    /**
     * 查看学生的答题情况
     * @param student
     * @param studentExamInfo
     * @return
     */
    public List<CheckStudentExamInfo> checkStudentExamInfoService(Student student, StudentExamInfo studentExamInfo);
}
