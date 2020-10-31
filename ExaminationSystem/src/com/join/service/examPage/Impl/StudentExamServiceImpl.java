package com.join.service.examPage.Impl;

import com.join.dao.examPage.ExamBasicInfoDao;
import com.join.dao.examPage.Impl.StudentExamDaoImpl;
import com.join.dao.examPage.StudentExamDao;
import com.join.entity.*;
import com.join.service.examPage.StudentExamService;

import java.util.List;

public class StudentExamServiceImpl implements StudentExamService {
    @Override
    public ExamBasicInfo getExamInfoService(ExamBasicInfo examBasicInfo) {
        StudentExamDao studentExamDao=new StudentExamDaoImpl();
        return studentExamDao.getExamInfo(examBasicInfo);
    }

    @Override
    public List<ExamAboutQuestion> getQuestionInfoService(ExamBasicInfo examBasicInfo) {
        StudentExamDao studentExamDao=new StudentExamDaoImpl();
        return studentExamDao.getQuestionInfo(examBasicInfo);
    }

    @Override
    public boolean completedExamService(List<ExamAboutQuestion> list, Student student,String courseName) {
        StudentExamDao studentExamDao=new StudentExamDaoImpl();
        return studentExamDao.completedExam(list,student,courseName);
    }

    @Override
    public boolean submitExamService(List<ExamAboutQuestion> list, String courseName,Student student) {
        StudentExamDao studentExamDao=new StudentExamDaoImpl();
        return studentExamDao.submitExam(list,courseName,student);
    }

    @Override
    public List<CheckStudentExamInfo> checkStudentExamInfoService(Student student, StudentExamInfo studentExamInfo) {
        StudentExamDao studentExamDao=new StudentExamDaoImpl();
        return studentExamDao.checkStudentExamInfo(student,studentExamInfo);
    }
}
