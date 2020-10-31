package com.join.sql.examPage.examPageAll;

public class ExamBasicInfoSql {
    /**
     * 如果该试卷不存在试卷数据库中，则插入试卷的基本信息
     */
    public static String addExamPageAllByBasicInfo="insert into exambasicinfo (academy,major,grade,examName,examTime,teacherName) value (?,?,?,?,?,?)";
    /**
     * 查看创建的试卷是否重复
     */
    public static String queryExamPageExit="select * from exambasicinfo where academy=? and major=? and grade=? and examName=?";
    /**
     * 向试卷中插入试题
     */
    public static String addQuestion="insert into examaboutquestion (academy,major,grade,examName,questionType,questionContent,singleScore) value (?,?,?,?,?,?,?)";
    /**
     * 从试卷中删除试题
     */
    public static String deleteQuestion="delete from examaboutquestion where questionContent=?";

    /**
     * 如果取消创建试卷，则将两个表中的试卷相关信息都删除
     */
    public static String deleteExamPage="delete examaboutquestion,exambasicinfo from examaboutquestion inner join exambasicinfo on examaboutquestion.examName=exambasicinfo.examName where examaboutquestion.examName=?";

    /**
     * 如果确认添加试卷，则计算总分
     */
    public static String sumTotal="select sum(singleScore) from examaboutquestion where examName=?";

    /**
     * 将总分存储进试卷表中就添加试卷彻底结束
     */
    public static String storeExamPage="update exambasicinfo set totalScore=? where examName=?;";
}
