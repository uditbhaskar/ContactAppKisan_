package com.example.contactappkisan;

public class ContactDatabaseInfoJava {
    public ContactDatabaseInfoJava(String name, String time, String otp) {
        this.name = name;
        this.time = time;
        this.otp = otp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    private String name;
    private String time;
    private String otp;
}
