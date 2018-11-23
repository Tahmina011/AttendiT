package com.example.zedd.attendit;

public class RTeacher {
    String tName,tId,tBd,tPhone,tEmail,tGender,tUserID,tPassword;

    public RTeacher(String tName, String tId, String tBd, String tPhone, String tEmail, String tGender, String tUserID, String tPassword) {
        this.tName = tName;
        this.tId = tId;
        this.tBd = tBd;
        this.tPhone = tPhone;
        this.tEmail = tEmail;
        this.tGender = tGender;
        this.tUserID = tUserID;
        this.tPassword = tPassword;
    }
    public RTeacher()
    {

    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String gettBd() {
        return tBd;
    }

    public void settBd(String tBd) {
        this.tBd = tBd;
    }

    public String gettPhone() {
        return tPhone;
    }

    public void settPhone(String tPhone) {
        this.tPhone = tPhone;
    }

    public String gettEmail() {
        return tEmail;
    }

    public void settEmail(String tEmail) {
        this.tEmail = tEmail;
    }

    public String gettGender() {
        return tGender;
    }

    public void settGender(String tGender) {
        this.tGender = tGender;
    }

    public String gettUserID() {
        return tUserID;
    }

    public void settUserID(String tUserID) {
        this.tUserID = tUserID;
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword;
    }
}
