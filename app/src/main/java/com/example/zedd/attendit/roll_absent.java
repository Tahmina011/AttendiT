package com.example.zedd.attendit;

public class roll_absent {
    String roll;
    boolean present;
    public roll_absent()
    {

    }

    public roll_absent(String roll, boolean present) {
        this.roll = roll;
        this.present = present;
    }

    public String getRoll() {
        return roll;
    }

    public void setRoll(String roll) {
        this.roll = roll;
    }

    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }
}
