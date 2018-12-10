package com.example.zedd.attendit;

public class Status {

    String uid;
    String status;
    String idd;
public Status()
{

}
    public Status(String uid, String status, String idd) {

        this.uid = uid;
        this.status = status;
        this.idd = idd;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIdd() {
        return idd;
    }

    public void setIdd(String idd) {
        this.idd = idd;
    }
}
