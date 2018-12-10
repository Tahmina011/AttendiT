package com.example.zedd.attendit;

public class courseTeacherCount {
    String dept;
    int tcount;

    public courseTeacherCount(String dept, int tcount) {
        this.dept = dept;
        this.tcount = tcount;
    }
    public courseTeacherCount()
    {

    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public int getTcount() {
        return tcount;
    }

    public void setTcount(int tcount) {
        this.tcount = tcount;
    }
}
