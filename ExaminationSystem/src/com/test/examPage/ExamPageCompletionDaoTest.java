package com.test.examPage;

import com.join.dao.examPage.ExamPageCompletionDao;
import com.join.dao.examPage.ExamPageSingleDao;
import com.join.dao.examPage.Impl.ExamPageCompletionDaoImpl;
import com.join.dao.examPage.Impl.ExamPageSingleDaoImpl;
import com.join.entity.ExamPageCompletion;
import com.join.entity.ExamPageSingle;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExamPageCompletionDaoTest {
    ExamPageCompletionDao examPageCompletionDao=new ExamPageCompletionDaoImpl();

    @Test
    public void testAddCompletionByInfo() {
        ExamPageCompletion examPageCompletion=new ExamPageCompletion(8,"计算机组成原理","CPU从内存中取出一条指令并执行该指令的时间称为____.","指令周期",1,"join");
        boolean flag=examPageCompletionDao.addCompletionByInfo(examPageCompletion,"join");
        if (flag) {
            System.out.println("插入填空题成功");
        } else {
            System.out.println("插入填空题失败");
        }
    }

    @Test
    public void testDeleteCompletionInfo() {
        ExamPageCompletion examPageCompletion=new ExamPageCompletion(8,"计算机组成原理","CPU从内存中取出一条指令并执行该指令的时间称为____.","指令周期",1,"join");
        boolean flag=examPageCompletionDao.deleteCompletionInfo(examPageCompletion.getCourseName(),examPageCompletion.getQuestionContent());
        if (flag) {
            System.out.println("删除填空题成功");
        } else {
            System.out.println("删除删除题失败");
        }
    }

    @Test
    public void testQueryCompletionByTwoInfo() {
        ExamPageCompletion examPageCompletion=new ExamPageCompletion(8,"计算机组成原理","CPU从内存中取出一条指令并执行该指令的时间称为____.","指令周期",1,"join");
        ExamPageCompletion examPageCompletion1=examPageCompletionDao.queryCompletionByTwoInfo(examPageCompletion.getCourseName(),examPageCompletion.getQuestionContent());
        if (examPageCompletion1==null) {
            System.out.println("查询失败，无该填空题");
        } else {
            System.out.println("查询成功 "+examPageCompletion1);
        }
    }

    @Test
    public void testListAllExamPageCompletion() {
        List<ExamPageCompletion> list=new ArrayList<>();
        list=examPageCompletionDao.listAllExamPageCompletion();
        if (list==null) {
            System.out.println("查询失败");
        } else {
            for (ExamPageCompletion list1:list) {
                System.out.println(list1);
            }
        }
    }
}
