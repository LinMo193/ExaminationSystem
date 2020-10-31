package com.test.examPage;

import com.join.dao.examPage.ExamPageCompletionDao;
import com.join.dao.examPage.ExamPageShortDao;
import com.join.dao.examPage.Impl.ExamPageCompletionDaoImpl;
import com.join.dao.examPage.Impl.ExamPageShortDaoImpl;
import com.join.entity.ExamPageCompletion;
import com.join.entity.ExamPageShort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExamPageShortDaoTest {
    ExamPageShortDao examPageShortDao=new ExamPageShortDaoImpl();

    @Test
    public void testAddShortByInfo() {
        ExamPageShort examPageShort=new ExamPageShort(9,"计算机组成原理","什么是总线？总线主要有哪些特性？",1,"join");
        boolean flag=examPageShortDao.addShortByInfo(examPageShort,"join");
        if (flag) {
            System.out.println("插入简答题成功");
        } else {
            System.out.println("插入简答题失败");
        }
    }

    @Test
    public void testDeleteShortInfo() {
        ExamPageShort examPageShort=new ExamPageShort(9,"计算机组成原理","什么是总线？总线主要有哪些特性？",1,"join");
        boolean flag=examPageShortDao.deleteShortInfo(examPageShort.getCourseName(),examPageShort.getQuestionContent());
        if (flag) {
            System.out.println("删除简答题成功");
        } else {
            System.out.println("删除简答题失败");
        }
    }

    @Test
    public void testQueryShortByTwoInfo() {
        ExamPageShort examPageShort=new ExamPageShort(9,"计算机组成原理","什么是总线？总线主要有哪些特性？",1,"join");
        ExamPageShort examPageShort1=examPageShortDao.queryShortByTwoInfo(examPageShort.getCourseName(),examPageShort.getQuestionContent());
        if (examPageShort1==null) {
            System.out.println("查询失败，无该简答题");
        } else {
            System.out.println("查询成功 "+examPageShort1);
        }
    }

    @Test
    public void testListAllExamPageShort() {
        List<ExamPageShort> list=new ArrayList<>();
        list=examPageShortDao.listAllExamPageShort();
        if (list==null) {
            System.out.println("查询失败");
        } else {
            for (ExamPageShort list1:list) {
                System.out.println(list1);
            }
        }
    }
}
