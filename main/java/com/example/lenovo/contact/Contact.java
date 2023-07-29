package com.example.lenovo.contact;

public class Contact {
   private String fname;
    private String lname;
   private String phone;
    private String job;
    private String email;
   private int id;

    public Contact() {
    }

    public Contact(String fname,String lname, String phone,String job,String email) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.job = job;
        this.email = email;
    }

    public Contact(int id,String fname,String lname, String phone,String job,String email) {
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.job = job;
        this.email = email;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFName() {
        return fname;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }
    public String getLName() {
        return lname;
    }

    public void setLName(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
