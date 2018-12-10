package com.example.zedd.attendit;

public class courseStudentCount {
    String ccourseNo;

    int count;
    public courseStudentCount()
    {

    }

    public courseStudentCount(String ccourseNo, int count) {
        this.ccourseNo = ccourseNo;

        this.count = count;
    }

    public String getCcourseNo() {
        return ccourseNo;
    }

    public void setCcourseNo(String ccourseNo) {
        this.ccourseNo = ccourseNo;
    }




    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
