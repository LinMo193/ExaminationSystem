package com.join.service.student;

import com.join.entity.Student;

public interface StudentService {
    // 增
    public boolean addStudentByAllInfo(Student student);
    // 查student全部信息
    public Student queryStudentByInfoService(String username,String password);
    // 查student的sno是否唯一
    public boolean queryStudentBySnoService(int sno);
}