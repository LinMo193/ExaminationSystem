package com.join.entity;

public class ExamAboutQuestion {
    private String academy;
    private String major;
    private int grade;
    private String examName;
    private int examTime;
    private String teacherName;

    private String questionContent;
    private String questionType;
    private int singleScore;

    private String studentAnswer;

    public String getStudentAnswer() {
        return studentAnswer;
    }

    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }

    public ExamAboutQuestion() {
    }

    public ExamAboutQuestion(String academy, String major, int grade, String examName, int examTime, String teacherName) {
        this.academy = academy;
        this.major = major;
        this.grade = grade;
        this.examName = examName;
        this.examTime = examTime;
        this.teacherName = teacherName;
    }

    public ExamAboutQuestion(String academy, String major, int grade, String examName, int examTime, String teacherName, String questionContent, String questionType, int singleScore, String studentAnswer) {
        this.academy = academy;
        this.major = major;
        this.grade = grade;
        this.examName = examName;
        this.examTime = examTime;
        this.teacherName = teacherName;
        this.questionContent = questionContent;
        this.questionType = questionType;
        this.singleScore = singleScore;
        this.studentAnswer = studentAnswer;
    }

    public ExamAboutQuestion(String academy, String major, int grade, String examName, int examTime, String teacherName, String questionContent, String questionType, int singleScore) {
        this.academy = academy;
        this.major = major;
        this.grade = grade;
        this.examName = examName;
        this.examTime = examTime;
        this.teacherName = teacherName;
        this.questionContent = questionContent;
        this.questionType = questionType;
        this.singleScore = singleScore;
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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public int getSingleScore() {
        return singleScore;
    }

    public void setSingleScore(int singleScore) {
        this.singleScore = singleScore;
    }

    @Override
    public String toString() {
        return "ExamBasicInfo{" +
                "academy='" + academy + '\'' +
                ", major='" + major + '\'' +
                ", grade=" + grade +
                ", examName='" + examName + '\'' +
                ", examTime=" + examTime +
                ", teacherName='" + teacherName + '\'' +
                ", questionContent='" + questionContent + '\'' +
                ", questionType='" + questionType + '\'' +
                ", singleScore=" + singleScore +
                '}';
    }
}
