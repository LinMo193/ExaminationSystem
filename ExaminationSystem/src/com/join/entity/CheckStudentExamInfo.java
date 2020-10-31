package com.join.entity;

import javafx.beans.binding.StringExpression;

public class CheckStudentExamInfo {
    private int sno;
    private String username;
    private String examName;
    private String questionContent;
    private String answerRight;
    private String studentAnswer;
    private String questionType;

    public CheckStudentExamInfo() {
    }

    public CheckStudentExamInfo(int sno, String username, String examName, String questionContent, String answerRight, String studentAnswer, String questionType) {
        this.sno = sno;
        this.username = username;
        this.examName = examName;
        this.questionContent = questionContent;
        this.answerRight = answerRight;
        this.studentAnswer = studentAnswer;
        this.questionType = questionType;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getAnswerRight() {
        return answerRight;
    }

    public void setAnswerRight(String answerRight) {
        this.answerRight = answerRight;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }
}
