package com.join.service.student.Impl;

import com.join.dao.student.Impl.StudentDaoImpl;
import com.join.dao.student.StudentDao;
import com.join.entity.Student;
import com.join.service.student.StudentService;

public class StudentServiceImpl implements StudentService {
    @Override
    public boolean addStudentByAllInfo(Student student) {
        StudentDao studentDao=new StudentDaoImpl();
        return studentDao.addStudentByAllInfo(student);
    }

    @Override
    public Student queryStudentByInfoService(String username, String password) {
        StudentDao studentDao=new StudentDaoImpl();
        return studentDao.queryStudentByInfo(username,password);
    }

    @Override
    public boolean queryStudentBySnoService(int sno) {
        StudentDao studentDao=new StudentDaoImpl();
        return studentDao.queryStudentBySno(sno);
    }
}
