package com.join.dao.examPage;

import com.join.entity.ExamAboutQuestion;

public interface ExamBasicInfoDao {
    /**
     * 创建试卷的基本信息，例如学院、专业、年级、试卷题目，考试时间
     *
     * @param examAboutQuestion
     * @return
     */
    public boolean addExamPageAllByBasicInfo(ExamAboutQuestion examAboutQuestion);

    /**
     * 查看创建的试卷是否重复
     *
     * @param examAboutQuestion
     * @return
     */
    public boolean queryExamPageAllExit(ExamAboutQuestion examAboutQuestion);

    /**
     * 向试卷添加试题
     *
     * @param examAboutQuestion
     * @return
     */
    public int addQuestion(ExamAboutQuestion examAboutQuestion, int count);

    /**
     * 从试卷删除试题
     *
     * @param examAboutQuestion
     * @return
     */
    public int deleteQuestion(ExamAboutQuestion examAboutQuestion, int count);

    /**
     * 如果点击取消按钮，则删除试卷
     *
     * @param examAboutQuestion
     * @return
     */
    public boolean deleteExamPage(ExamAboutQuestion examAboutQuestion);

    /**
     * 如果确认添加试卷，则计算总分
     *
     * @param examAboutQuestion
     * @return
     */
    public int sumTotal(ExamAboutQuestion examAboutQuestion);

    /**
     * 将总分存储进试卷表中就添加试卷彻底结束
     *
     * @param examAboutQuestion
     * @param sum
     * @return
     */
    public boolean storeExamPage(ExamAboutQuestion examAboutQuestion, int sum);
}
