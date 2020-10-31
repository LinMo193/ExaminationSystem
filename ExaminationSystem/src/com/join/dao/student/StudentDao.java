package com.join.dao.student;

import com.join.entity.Student;

public interface StudentDao {
    // 增
    public boolean addStudentByAllInfo(Student student);
    // 删
    // 改
    // 查student全部信息
    public Student queryStudentByInfo(String username,String password);
    // 查student的sno是否唯一
    public boolean queryStudentBySno(int sno);
}
