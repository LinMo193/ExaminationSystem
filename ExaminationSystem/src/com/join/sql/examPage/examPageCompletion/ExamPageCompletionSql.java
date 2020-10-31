package com.join.sql.examPage.examPageCompletion;

public class ExamPageCompletionSql {
    /**
     * 添加填空题
     */
    public static String addCompletionByInfo="insert into questioncompletioninfo (courseName,questionContent,answerRight,degree,teacherName) value (?,?,?,?,?)";
    /**
     * 删除填空题
     */
    public static String deleteCompletionByInfo="delete from questioncompletioninfo where courseName=? and questionContent=?";
    /**
     * 根据课程名称和试题内容查询是否存在
     */
    public static String queryCompletionByTwoInfo="select * from questioncompletioninfo where courseName=? and questionContent=?";
    /**
     * 查询表中全部填空题
     */
    public static String queryCompletionAllByInfo="select * from questioncompletioninfo order by rand()";

    /**
     * 分页查询填空题
     */
    public static String queryExamPageCompletionByPage="select * from questioncompletioninfo limit ?,?";

    /**
     * 查询表中填空题总数
     */
    public static String examPageCompletionTotalCount="select count(*) from questioncompletioninfo";
}
