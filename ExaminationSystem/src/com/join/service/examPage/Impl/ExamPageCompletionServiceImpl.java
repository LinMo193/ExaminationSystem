package com.join.service.examPage.Impl;

import com.join.dao.examPage.ExamPageCompletionDao;
import com.join.dao.examPage.ExamPageSingleDao;
import com.join.dao.examPage.Impl.ExamPageCompletionDaoImpl;
import com.join.dao.examPage.Impl.ExamPageSingleDaoImpl;
import com.join.entity.ExamPageCompletion;
import com.join.service.examPage.ExamPageCompletionService;

import java.util.List;

public class ExamPageCompletionServiceImpl implements ExamPageCompletionService {
    @Override
    public boolean addCompletionByInfoService(ExamPageCompletion examPageCompletion,String teacherName) {
        ExamPageCompletionDao examPageCompletionDao=new ExamPageCompletionDaoImpl();
        return examPageCompletionDao.addCompletionByInfo(examPageCompletion,teacherName);
    }

    @Override
    public boolean deleteSingleByInfoService(String courseName, String questionContent) {
        ExamPageCompletionDao examPageCompletionDao=new ExamPageCompletionDaoImpl();
        return examPageCompletionDao.deleteCompletionInfo(courseName,questionContent);
    }

    @Override
    public ExamPageCompletion queryCompletionByTwoInfoService(String courseName, String questionContent) {
        ExamPageCompletionDao examPageCompletionDao=new ExamPageCompletionDaoImpl();
        return examPageCompletionDao.queryCompletionByTwoInfo(courseName,questionContent);
    }

    @Override
    public List<ExamPageCompletion> listAllExamPageCompletionService() {
        ExamPageCompletionDao examPageCompletionDao=new ExamPageCompletionDaoImpl();
        return examPageCompletionDao.listAllExamPageCompletion();
    }

    @Override
    public List<ExamPageCompletion> queryExamPageCompletionByPage(int currentPage) {
        ExamPageCompletionDao examPageCompletionDao=new ExamPageCompletionDaoImpl();
        return examPageCompletionDao.queryExamPageCompletionByPage(currentPage);
    }

    @Override
    public int getExamPageCompletionTotalCount() {
        ExamPageCompletionDao examPageCompletionDao=new ExamPageCompletionDaoImpl();
        return examPageCompletionDao.getExamPageCompletionTotalCount();
    }
}
