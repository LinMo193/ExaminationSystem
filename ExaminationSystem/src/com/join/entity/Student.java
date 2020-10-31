package com.join.entity;

public class Student {
    private int sno;
    private String username;
    private String password;
    private String telenumber;
    private String email;
    private String headpath;

    public Student() {
    }

    public Student(int sno, String username, String password, String telenumber, String email, String headpath) {
        this.sno = sno;
        this.username = username;
        this.password = password;
        this.telenumber = telenumber;
        this.email = email;
        this.headpath = headpath;
    }

    public String getTelenumber() {
        return telenumber;
    }

    public void setTelenumber(String telenumber) {
        this.telenumber = telenumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getHeadpath() {
        return headpath;
    }

    public void setHeadpath(String headpath) {
        this.headpath = headpath;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno=" + sno +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", telenumber='" + telenumber + '\'' +
                ", email='" + email + '\'' +
                ", headpath='" + headpath + '\'' +
                '}';
    }
}
