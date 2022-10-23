package com.example.noba;

import java.util.Date;

public class User{
    private sqlcon con=new sqlcon();
    private int id;
    private String username;
    private Date birthday;
    private int phonenumber;
    private String email;
    private String URL;
    private Date join_time;

    public User(int id, String username, Date birthday, int phonenumber, String email, String URL,Date join_time) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
        this.phonenumber = phonenumber;
        this.email = email;
        this.URL = URL;
        this.join_time = join_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPhone() {
        return phonenumber;
    }

    public void setPhone(int phonenumber) {
        this.phonenumber = phonenumber;
    }
}