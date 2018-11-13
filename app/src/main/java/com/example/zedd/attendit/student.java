package com.example.zedd.attendit;

public class student {
    int _id;
    String roll;
    String name;
    String phone;
    float cgpa;



    public student()
    {


    }
    public student(int _id,String roll,String name,String phone,float cgpa)
    {
        this._id=_id;
        this.roll=roll;
        this.name=name;
        this.phone=phone;
        this.cgpa=cgpa;
    }
    public student(String roll,String name,String phone,float cgpa)
    {
        this.roll=roll;
        this.name=name;
        this.phone=phone;
        this.cgpa=cgpa;
    }
    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public float getCgpa() {
        return cgpa;
    }

    public String getRoll() {
        return roll;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCgpa(float cgpa) {
        this.cgpa = cgpa;
    }
}
