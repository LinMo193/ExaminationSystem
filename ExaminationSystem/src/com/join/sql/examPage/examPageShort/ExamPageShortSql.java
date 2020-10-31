package com.join.sql.examPage.examPageShort;

public class ExamPageShortSql {
    /**
     * 添加简答题
     */
    public static String addShortByInfo="insert into questionshortinfo (courseName,questionContent,degree,teacherName) value (?,?,?,?)";
    /**
     * 删除简答题
     */
    public static String deleteShortByInfo="delete from questionshortinfo where courseName=? and questionContent=?";
    /**
     * 根据课程名称和试题内容查询是否存在
     */
    public static String queryShortByTwoInfo="select * from questionshortinfo where courseName=? and questionContent=?";
    /**
     * 查询表中全部简答题
     */
    public static String queryShortAllByInfo="select * from questionshortinfo order by rand()";

    /**
     * 分页查询简答题
     */
    public static String queryExamPageShortByPage="select * from questionshortinfo limit ?,?";

    /**
     * 查询表中简答题总数
     */
    public static String examPageShortTotalCount="select count(*) from questionshortinfo";
}
