package com.join.entity;

public class StudentExamInfo {
    private int sno;
    private String username;
    private String examName;
    private int totalScore;

    public StudentExamInfo() {
    }

    public StudentExamInfo(int sno, String username, String examName, int totalScore) {
        this.sno = sno;
        this.username = username;
        this.examName = examName;
        this.totalScore = totalScore;
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

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    @Override
    public String toString() {
        return "StudentExamInfo{" +
                "sno=" + sno +
                ", username='" + username + '\'' +
                ", examName='" + examName + '\'' +
                ", totalScore=" + totalScore +
                '}';
    }
}
