package com.example.zedd.attendit;

public class code {
    String course;
    String codee;
    public code()
    {

    }

    public code(String course, String code) {
        this.course = course;
        this.codee = code;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCode() {
        return codee;
    }

    public void setCode(String code) {
        this.codee = code;
    }
}
