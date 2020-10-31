package com.join.sql.examPage.examPageAll;

public class StudentExamSql {
    /**
     * 学生考试时先获取该试卷的信息
     */
    public static String getExamInfo="select * from exambasicinfo where examName=?";

    /**
     * 返回试卷中所有的试题
     */
    public static String getQuestionInfo="select * from examaboutquestion where examName=?";

    /**
     * 学生提交试卷后将答题信息存储到数据库中
     */
    public static String completedExam="insert into studentallscore (sno,examName,questionContent,studentAnswer,questionType) value (?,?,?,?,?)";

    /**
     * 学生提交后计算得分（单选题，填空题）
     */
    public static String submitExam="insert into studentexamscore (sno,username,examName,actualScore) value (?,?,?,?)";

    /**
     * 从试卷信息中获取试题内容，试题类型与答案
     */
    public static String selectThreeInfo="select studentAnswer,questionType from studentallscore where examName=? and questionContent=?";

    /**
     * 获取该题的分值
     */
    public static String getSingleScore="select singleScore from examaboutquestion where questionContent=?";
    /**
     * 检验单选题答案是否正确
     */
    public static String checkedSingle="select answerRight from questionsingleinfo where questionContent=?";

    /**
     * 检验填空题答案是否正确
     */
    public static String checkedCompletion="select answerRight from questioncompletioninfo where questionContent=?";

    /**
     * 查询学生答题情况
     */
    public static String checkStudentExamInfo="select questionContent,studentAnswer,questionType from studentallscore where sno=? and examName=?";
}
