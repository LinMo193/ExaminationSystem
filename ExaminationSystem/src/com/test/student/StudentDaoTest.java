package com.test.student;

import com.join.dao.student.Impl.StudentDaoImpl;
import com.join.dao.student.StudentDao;
import com.join.entity.Student;
import org.junit.Test;

public class StudentDaoTest {
    StudentDao studentDao=new StudentDaoImpl();

    @Test
    public void testQueryStudentByInfo() {
        Student student=studentDao.queryStudentByInfo("张三","123");
        if (student!=null) {
            System.out.println("用户查询成功");
        } else {
            System.out.println("用户查询失败");
        }
    }

    @Test
    public void testAddStudentByAllInfo() {
        Student student=new Student(2019014015,"王五","789","15538921898","572201079@qq.com","C:\\Users\\asus\\Pictures\\2016-06\\225I2A33-5.jpg");
        boolean flag=studentDao.addStudentByAllInfo(student);
        if (flag) {
            System.out.println("用户注册成功");
        } else {
            System.out.println("用户注册失败");
        }
    }
}
