package com.example.zedd.attendit;

public class class_teacher_location {
    String class_time;
    String course_name;
    String teacher_id;
    double lattii;
    double longii;

    public class_teacher_location() {
    }

    public class_teacher_location(String class_time, String course_name, String teacher_id, double lattii, double longii) {
        this.class_time = class_time;
        this.course_name = course_name;
        this.teacher_id = teacher_id;
        this.lattii = lattii;
        this.longii = longii;
    }

    public String getClass_time() {
        return class_time;
    }

    public void setClass_time(String class_time) {
        this.class_time = class_time;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public double getLattii() {
        return lattii;
    }

    public void setLattii(double lattii) {
        this.lattii = lattii;
    }

    public double getLongii() {
        return longii;
    }

    public void setLongii(double longii) {
        this.longii = longii;
    }
}
