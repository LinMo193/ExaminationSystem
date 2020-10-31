package com.test.examPage;

import com.join.dao.examPage.ExamPageSingleDao;
import com.join.dao.examPage.Impl.ExamPageSingleDaoImpl;
import com.join.entity.ExamPageSingle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExamPageSingleDaoTest {
    ExamPageSingleDao examPageSingleDao=new ExamPageSingleDaoImpl();

    @Test
    public void testAddSingleByInfo() {
        ExamPageSingle examPageSingle=new ExamPageSingle(7,"计算机组成原理","从器件角度看，计算机经历了五代变化。但从系统结构看，至今绝大多数计算机仍属于（ ）计算机.","并行","冯·诺伊曼","智能","串行","冯·诺伊曼",1,"join");
        String teacherName="join";
        boolean flag=examPageSingleDao.addSingleByInfo(examPageSingle,teacherName);
        if (flag) {
            System.out.println("插入单选题成功");
        } else {
            System.out.println("插入单选题失败");
        }
    }

    @Test
    public void testDeleteSingleInfo() {
        ExamPageSingle examPageSingle=new ExamPageSingle(7,"计算机组成原理","从器件角度看，计算机经历了五代变化。但从系统结构看，至今绝大多数计算机仍属于（ ）计算机.","并行","冯·诺伊曼","智能","串行","冯·诺伊曼",1,"join");
        boolean flag=examPageSingleDao.deleteSingleInfo(examPageSingle.getCourseName(),examPageSingle.getQuestionContent());
        if (flag) {
            System.out.println("删除单选题成功");
        } else {
            System.out.println("删除单选题失败");
        }
    }

    @Test
    public void testQuerySingleByTwoInfo() {
        ExamPageSingle examPageSingle=new ExamPageSingle(7,"计算机组成原理","从器件角度看，计算机经历了五代变化。但从系统结构看，至今绝大多数计算机仍属于（ ）计算机.","并行","冯·诺伊曼","智能","串行","冯·诺伊曼",1,"join");
        ExamPageSingle examPageSingle1=examPageSingleDao.querySingleByTwoInfo(examPageSingle.getCourseName(),examPageSingle.getQuestionContent());
        if (examPageSingle1==null) {
            System.out.println("查询失败，无该单选题");
        } else {
            System.out.println("查询成功 "+examPageSingle1);
        }
    }

    @Test
    public void testListAllExamPageSingle() {
        List<ExamPageSingle> list=new ArrayList<>();
        list=examPageSingleDao.listAllExamPageSingle();
        if (list==null) {
            System.out.println("查询失败");
        } else {
             for (ExamPageSingle list1:list) {
                 System.out.println(list1);
             }
        }
    }
}
