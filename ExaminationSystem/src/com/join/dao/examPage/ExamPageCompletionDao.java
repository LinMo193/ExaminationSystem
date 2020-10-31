package com.join.dao.examPage;

import com.join.entity.ExamPageCompletion;
import com.join.entity.ExamPageSingle;

import java.util.List;

public interface ExamPageCompletionDao {
    // 增加填空题
    public boolean addCompletionByInfo(ExamPageCompletion examPageCompletion,String teacherName);
    // 删除填空题
    public boolean deleteCompletionInfo(String courseName,String questionContent);
    // 根据课程名称和试题内容查询是否存在
    public ExamPageCompletion queryCompletionByTwoInfo(String courseName,String questionContent);
    // 显示所有试题信息
    public List<ExamPageCompletion> listAllExamPageCompletion();
    // 分页展示所有填空题信息
    public List<ExamPageCompletion> queryExamPageCompletionByPage(int currentPage);
    // 计算共有多少填空题
    public int getExamPageCompletionTotalCount ();
}
