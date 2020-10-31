package com.join.service.teacher.Impl;

import com.join.dao.teacher.Impl.TeacherDaoImpl;
import com.join.dao.teacher.TeacherDao;
import com.join.entity.StudentExamInfo;
import com.join.service.teacher.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    @Override
    public List<StudentExamInfo> searchStudentExamInfoService() {
        TeacherDao teacherDao=new TeacherDaoImpl();
        return  teacherDao.searchStudentExamInfo();
    }

    @Override
    public StudentExamInfo searchOneStudentService(StudentExamInfo studentExamInfo) {
        TeacherDao teacherDao=new TeacherDaoImpl();
        return teacherDao.searchOneStudent(studentExamInfo);
    }
}
