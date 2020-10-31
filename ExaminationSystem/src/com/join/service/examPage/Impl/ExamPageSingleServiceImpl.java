package com.join.service.examPage.Impl;

import com.join.dao.examPage.ExamPageShortDao;
import com.join.dao.examPage.ExamPageSingleDao;
import com.join.dao.examPage.Impl.ExamPageShortDaoImpl;
import com.join.dao.examPage.Impl.ExamPageSingleDaoImpl;
import com.join.entity.ExamPageSingle;
import com.join.service.examPage.ExamPageSingleService;

import java.util.ArrayList;
import java.util.List;

public class ExamPageSingleServiceImpl implements ExamPageSingleService {
    @Override
    public boolean addSingleByInfoService(ExamPageSingle examPageSingle,String teacherName) {
        ExamPageSingleDao examPageSingleDao=new ExamPageSingleDaoImpl();
        return examPageSingleDao.addSingleByInfo(examPageSingle,teacherName);
    }

    @Override
    public boolean deleteSingleByInfoService(String courseName, String questionContent) {
        ExamPageSingleDao examPageSingleDao=new ExamPageSingleDaoImpl();
        return examPageSingleDao.deleteSingleInfo(courseName,questionContent);
    }

    @Override
    public ExamPageSingle querySingleByTwoInfoService(String courseName, String questionContent) {
        ExamPageSingleDao examPageSingleDao=new ExamPageSingleDaoImpl();
        return examPageSingleDao.querySingleByTwoInfo(courseName,questionContent);
    }

    @Override
    public List<ExamPageSingle> listAllExamPageSingleService() {
        ExamPageSingleDao examPageSingleDao=new ExamPageSingleDaoImpl();
        return examPageSingleDao.listAllExamPageSingle();
    }

    @Override
    public List<ExamPageSingle> queryExamPageSingleByPage(int currentPage) {
        ExamPageSingleDao examPageSingleDao=new ExamPageSingleDaoImpl();
        return examPageSingleDao.queryExamPageSingleByPage(currentPage);
    }

    @Override
    public int getExamPageSingleTotalCount() {
        ExamPageSingleDao examPageSingleDao=new ExamPageSingleDaoImpl();
        return examPageSingleDao.getExamPageSingleTotalCount();
    }
}
