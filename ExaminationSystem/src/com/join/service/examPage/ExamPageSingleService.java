package com.join.service.examPage;

import com.join.entity.ExamPageSingle;

import java.util.List;

public interface ExamPageSingleService {
    // 添加单选题
    public boolean addSingleByInfoService(ExamPageSingle examPageSingle,String teacherName);
    // 删除单选题
    public boolean deleteSingleByInfoService(String courseName,String questionContent);
    // 根据课程名称和试题内容查询是否存在
    public ExamPageSingle querySingleByTwoInfoService(String courseName,String questionContent);
    // 显示所有单选题
    public List<ExamPageSingle> listAllExamPageSingleService();
    // 分页展示所有单选题信息
    public List<ExamPageSingle> queryExamPageSingleByPage(int currentPage);
    // 计算共有多少单选题
    public int getExamPageSingleTotalCount ();
}
