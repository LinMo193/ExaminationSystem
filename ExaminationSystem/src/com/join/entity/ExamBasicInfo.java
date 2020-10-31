package com.join.entity;

public class ExamBasicInfo {
    private String academy;
    private String major;
    private int grade;
    private String examName;
    private int examTime;
    private int totalScore;
    private String teacherName;

    public ExamBasicInfo() {
    }

    public ExamBasicInfo(String academy, String major, int grade, String examName, int examTime, int totalScore, String teacherName) {
        this.academy = academy;
        this.major = major;
        this.grade = grade;
        this.examName = examName;
        this.examTime = examTime;
        this.totalScore = totalScore;
        this.teacherName = teacherName;
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public int getExamTime() {
        return examTime;
    }

    public void setExamTime(int examTime) {
        this.examTime = examTime;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "ExamBasicInfo{" +
                "academy='" + academy + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                ", examName='" + examName + '\'' +
                ", examTime=" + examTime +
                ", totalScore=" + totalScore +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
