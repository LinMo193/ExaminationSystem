package com.join.entity;

public class ExamPageShort {
    private int id;
    private String courseName;
    private String questionContent;
    private float degree;
    private String teacherName;

    public ExamPageShort() {
    }

    public ExamPageShort(int id, String courseName, String questionContent, float degree, String teacherName) {
        this.id = id;
        this.courseName = courseName;
        this.questionContent = questionContent;
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

    public float getDegree() {
        return degree;
    }

    public void setDegree(float degree) {
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
        return "ExamPageShort{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", degree=" + degree +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
