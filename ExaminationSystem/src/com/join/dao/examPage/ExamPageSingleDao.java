package com.join.dao.examPage;

import com.join.entity.ExamPageSingle;

import java.util.List;

public interface ExamPageSingleDao {
    // 增加单选题
    public boolean addSingleByInfo(ExamPageSingle examPageSingle,String teacherName);
    // 删除单选题
    public boolean deleteSingleInfo(String courseName,String questionContent);
    // 根据课程名称和试题内容查询是否存在
    public ExamPageSingle querySingleByTwoInfo(String courseName,String questionContent);
    // 显示所有试题信息
    public List<ExamPageSingle> listAllExamPageSingle();
    // 分页展示所有单选题信息
    public List<ExamPageSingle> queryExamPageSingleByPage(int currentPage);
    // 计算共有多少单选题
    public int getExamPageSingleTotalCount ();
}
