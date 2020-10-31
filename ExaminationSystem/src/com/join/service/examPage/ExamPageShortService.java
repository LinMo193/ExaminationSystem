package com.join.service.examPage;

import com.join.entity.ExamPageShort;
import com.join.entity.ExamPageSingle;

import java.util.List;

public interface ExamPageShortService {
    // 添加简答题
    public boolean addShortByInfoService(ExamPageShort examPageShort,String teacherName);
    // 删除简答题
    public boolean deleteShortByInfoService(String courseName,String questionContent);
    // 根据课程名称和试题内容查询是否存在
    public ExamPageShort queryShortByTwoInfoService(String courseName,String questionContent);
    // 显示所有简答题
    public List<ExamPageShort> listAllExamPageShortService();
    // 分页展示所有简答题信息
    public List<ExamPageShort> queryExamPageShortByPage(int currentPage);
    // 计算共有多少简答题
    public int getExamPageShortTotalCount ();
}
