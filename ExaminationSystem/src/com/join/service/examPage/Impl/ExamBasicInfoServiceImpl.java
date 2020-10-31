package com.join.service.examPage.Impl;

import com.join.dao.examPage.ExamBasicInfoDao;
import com.join.dao.examPage.Impl.ExamBasicInfoDaoImpl;
import com.join.entity.ExamAboutQuestion;
import com.join.service.examPage.ExamBasicInfoService;

public class ExamBasicInfoServiceImpl implements ExamBasicInfoService {
    @Override
    public boolean addExamPageAllByBasicInfoService(ExamAboutQuestion examAboutQuestion) {
        ExamBasicInfoDao examBasicInfoDao=new ExamBasicInfoDaoImpl();
        return examBasicInfoDao.addExamPageAllByBasicInfo(examAboutQuestion);
    }

    @Override
    public boolean queryExamPageAllExitService(ExamAboutQuestion examAboutQuestion) {
        ExamBasicInfoDao examBasicInfoDao=new ExamBasicInfoDaoImpl();
        return examBasicInfoDao.queryExamPageAllExit(examAboutQuestion);
    }

    @Override
    public int addQuestionService(ExamAboutQuestion examAboutQuestion, int count) {
        ExamBasicInfoDao examBasicInfoDao=new ExamBasicInfoDaoImpl();
        return examBasicInfoDao.addQuestion(examAboutQuestion,count);
    }

    @Override
    public int deleteQuestionService(ExamAboutQuestion examAboutQuestion, int count) {
        ExamBasicInfoDao examBasicInfoDao=new ExamBasicInfoDaoImpl();
        return examBasicInfoDao.deleteQuestion(examAboutQuestion,count);
    }

    @Override
    public boolean deleteExamPageService(ExamAboutQuestion examAboutQuestion) {
        ExamBasicInfoDao examBasicInfoDao=new ExamBasicInfoDaoImpl();
        return examBasicInfoDao.deleteExamPage(examAboutQuestion);
    }

    @Override
    public int sumTotalService(ExamAboutQuestion examAboutQuestion) {
        ExamBasicInfoDao examBasicInfoDao=new ExamBasicInfoDaoImpl();
        return examBasicInfoDao.sumTotal(examAboutQuestion);
    }

    @Override
    public boolean storeExamPageService(ExamAboutQuestion examAboutQuestion, int sum) {
        ExamBasicInfoDao examBasicInfoDao=new ExamBasicInfoDaoImpl();
        return examBasicInfoDao.storeExamPage(examAboutQuestion,sum);
    }
}
