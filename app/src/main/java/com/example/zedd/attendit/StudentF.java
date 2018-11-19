package com.example.zedd.attendit;

public class StudentF {
    String fname,fbirth,fgender,fuid,femail,fpass,froll,fphone;
    public StudentF(){

    };

    public StudentF(String fname, String fbirth, String fgender, String fuid, String femail, String fpass, String roll,String fphone) {
        this.fname = fname;
        this.fbirth = fbirth;
        this.fgender = fgender;
        this.fuid = fuid;
        this.femail = femail;
        this.fpass = fpass;
        this.froll = roll;
        this.fphone=fphone;
    }

    public String getFname() {
        return fname;
    }

    public String getFbirth() {
        return fbirth;
    }

    public void setFphone(String fphone) {
        this.fphone = fphone;
    }

    public String getFphone() {

        return fphone;
    }

    public String getFgender() {
        return fgender;
    }

    public String getFuid() {
        return fuid;
    }

    public String getFemail() {
        return femail;
    }

    public String getFpass() {
        return fpass;
    }

    public String getFroll() {
        return froll;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setFbirth(String fbirth) {
        this.fbirth = fbirth;
    }

    public void setFgender(String fgender) {
        this.fgender = fgender;
    }

    public void setFuid(String fuid) {
        this.fuid = fuid;
    }

    public void setFemail(String femail) {
        this.femail = femail;
    }

    public void setFpass(String fpass) {
        this.fpass = fpass;
    }

    public void setFroll(String roll) {
        this.froll = roll;
    }
}
