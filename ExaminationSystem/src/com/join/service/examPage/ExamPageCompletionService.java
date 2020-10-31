package com.join.service.examPage;

import com.join.entity.ExamPageCompletion;
import com.join.entity.ExamPageSingle;

import java.util.List;

public interface ExamPageCompletionService {
    // 添加填空题
    public boolean addCompletionByInfoService(ExamPageCompletion examPageCompletion,String teacherName);
    // 删除填空题
    public boolean deleteSingleByInfoService(String courseName,String questionContent);
    // 根据课程名称和试题内容查询是否存在
    public ExamPageCompletion queryCompletionByTwoInfoService(String courseName,String questionContent);
    // 显示所有填空题
    public List<ExamPageCompletion> listAllExamPageCompletionService();
    // 分页展示所有填空题信息
    public List<ExamPageCompletion> queryExamPageCompletionByPage(int currentPage);
    // 计算共有多少填空题
    public int getExamPageCompletionTotalCount ();
}
