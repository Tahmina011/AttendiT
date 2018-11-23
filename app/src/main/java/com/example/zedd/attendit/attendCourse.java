package com.example.zedd.attendit;

public class attendCourse {
    String courseNo;
    public attendCourse(){}

    public attendCourse(String courseNo) {
        this.courseNo = courseNo;
    }

    public String getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(String courseNo) {
        this.courseNo = courseNo;
    }
}
