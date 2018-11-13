package com.example.zedd.attendit;

public class course {
    String course_no;
    String course_title;
    public course()
    {

    }
    public course(String c,String t)
    {
        this.course_no=c;
        this.course_title=t;
    }

    public void setCourse_no(String course_no) {
        this.course_no = course_no;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_no() {
        return course_no;
    }

    public String getCourse_title() {
        return course_title;
    }
}
