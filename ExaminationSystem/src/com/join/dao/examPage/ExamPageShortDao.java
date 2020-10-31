package com.join.dao.examPage;

import com.join.entity.ExamPageShort;
import com.join.entity.ExamPageSingle;

import java.util.List;

public interface ExamPageShortDao {
    // 增加简单题
    public boolean addShortByInfo(ExamPageShort examPageShort,String teacherName);
    // 删除简答题
    public boolean deleteShortInfo(String courseName,String questionContent);
    // 根据课程名称和试题内容查询是否存在
    public ExamPageShort queryShortByTwoInfo(String courseName,String questionContent);
    // 显示所有试题信息
    public List<ExamPageShort> listAllExamPageShort();
    // 分页展示所有简答题信息
    public List<ExamPageShort> queryExamPageShortByPage(int currentPage);
    // 计算共有多少简答题
    public int getExamPageShortTotalCount ();
}
