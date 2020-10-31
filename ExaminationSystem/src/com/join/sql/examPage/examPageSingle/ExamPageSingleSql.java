package com.join.sql.examPage.examPageSingle;

public class ExamPageSingleSql {
    /**
     * 添加单选题
     */
    public static String addSingleByInfo="insert into questionsingleinfo (courseName,questionContent,answerA,answerB,answerC,answerD,answerRight,degree,teacherName) value (?,?,?,?,?,?,?,?,?)";
    /**
     * 删除单选题
     */
    public static String deleteSingleByInfo="delete from questionsingleinfo where courseName=? and questionContent=?";

    /**
     * 根据课程名称和试题内容查询是否存在或显示该试题的内容
     */
    public static String querySingleByTwoInfo="select * from questionsingleinfo where courseName=? and questionContent=?";

    /**
     * 查询所有单选题
     */
    public static String querySingleAllByInfo="select * from questionsingleinfo order by rand()";

    /**
     * 分页查询单选题
     */
    public static String queryExamPageSingleByPage="select * from questionsingleinfo limit ?,?";

    /**
     * 查询表中数据库总数
     */
    public static String examPageSingleTotalCount="select count(*) from questionsingleinfo";

}
