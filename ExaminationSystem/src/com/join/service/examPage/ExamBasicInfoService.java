package com.join.service.examPage;

import com.join.entity.ExamAboutQuestion;

public interface ExamBasicInfoService {
    /**
     * 创建试卷的基本信息，例如学院、专业、年级、试卷题目，考试时间，录题教师
     * @param examAboutQuestion
     * @return
     */
    public boolean addExamPageAllByBasicInfoService(ExamAboutQuestion examAboutQuestion);

    /**
     * 查看创建的试卷是否重复
     * @param examAboutQuestion
     * @return
     */
    public boolean queryExamPageAllExitService(ExamAboutQuestion examAboutQuestion);

    /**
     * 向试卷添加试题
     * @param examAboutQuestion
     * @return
     */
    public int addQuestionService(ExamAboutQuestion examAboutQuestion, int count);

    /**
     * 从试卷删除试题
     * @param examAboutQuestion
     * @return
     */
    public int deleteQuestionService(ExamAboutQuestion examAboutQuestion, int count);

    /**
     * 如果点击取消按钮，则删除试卷
     * @param examAboutQuestion
     * @return
     */
    public boolean deleteExamPageService(ExamAboutQuestion examAboutQuestion);

    /**
     * 如果确认添加试卷，则计算总分
     * @param examAboutQuestion
     * @return
     */
    public int sumTotalService(ExamAboutQuestion examAboutQuestion);

    /**
     * 将总分存储进试卷表中就添加试卷彻底结束
     * @param examAboutQuestion
     * @param sum
     * @return
     */
    public boolean storeExamPageService(ExamAboutQuestion examAboutQuestion, int sum);
}
