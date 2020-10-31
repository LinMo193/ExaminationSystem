package com.join.service.examPage.Impl;

import com.join.dao.examPage.ExamPageShortDao;
import com.join.dao.examPage.ExamPageSingleDao;
import com.join.dao.examPage.Impl.ExamPageShortDaoImpl;
import com.join.dao.examPage.Impl.ExamPageSingleDaoImpl;
import com.join.entity.ExamPageShort;
import com.join.service.examPage.ExamPageShortService;

import java.util.List;

public class ExamPageShortServiceImpl implements ExamPageShortService {

    @Override
    public boolean addShortByInfoService(ExamPageShort examPageShort,String teacherName) {
        ExamPageShortDao examPageShortDao=new ExamPageShortDaoImpl();
        return examPageShortDao.addShortByInfo(examPageShort,teacherName);
    }

    @Override
    public boolean deleteShortByInfoService(String courseName, String questionContent) {
        ExamPageShortDao examPageShortDao=new ExamPageShortDaoImpl();
        return examPageShortDao.deleteShortInfo(courseName,questionContent);
    }

    @Override
    public ExamPageShort queryShortByTwoInfoService(String courseName, String questionContent) {
        ExamPageShortDao examPageShortDao=new ExamPageShortDaoImpl();
        return examPageShortDao.queryShortByTwoInfo(courseName,questionContent);
    }

    @Override
    public List<ExamPageShort> listAllExamPageShortService() {
        ExamPageShortDao examPageShortDao=new ExamPageShortDaoImpl();
        return examPageShortDao.listAllExamPageShort();
    }

    @Override
    public List<ExamPageShort> queryExamPageShortByPage(int currentPage) {
        ExamPageShortDao examPageShortDao=new ExamPageShortDaoImpl();
        return examPageShortDao.queryExamPageShortByPage(currentPage);
    }

    @Override
    public int getExamPageShortTotalCount() {
        ExamPageShortDao examPageShortDao=new ExamPageShortDaoImpl();
        return examPageShortDao.getExamPageShortTotalCount();
    }
}
