package com.example.zedd.attendit;

public class StudentClassAmount {
    String roll;
    String classamount;
    public StudentClassAmount()
    {

    }

    public StudentClassAmount(String roll, String classamount) {
        this.roll = roll;
        this.classamount = classamount;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public String getClassamount() {
        return classamount;
    }

    public void setClassamount(String classamount) {
        this.classamount = classamount;
    }
}
