package com.join.entity;

public class ExamPageCompletion {
    private int id;
    private String courseName;
    private String questionContent;
    private String answerRight;
    private int degree;
    private String teacherName;

    public ExamPageCompletion() {
    }

    public ExamPageCompletion(int id, String courseName, String questionContent, String answerRight, int degree, String teacherName) {
        this.id = id;
        this.courseName = courseName;
        this.questionContent = questionContent;
        this.answerRight = answerRight;
        this.degree = degree;
        this.teacherName = teacherName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "ExamPageCompletion{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", answerRight='" + answerRight + '\'' +
                ", degree='" + degree + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
