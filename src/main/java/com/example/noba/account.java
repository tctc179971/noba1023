package com.example.noba;

public class account {
    private sqlcon con=new sqlcon();
    private int ID;
    private String account, password;

    public account(String account, String password) {
//        new Thread(() -> {
//            String IDsearchsql="Select a.ID From accounts a where a.account='"+account+"'";
//            int now_ID=Integer.parseInt(con.getinfo(IDsearchsql));
//            this.ID = now_ID;
//        }).start();
        this.account = account;
        this.password = password;
    }
    public int getID() {
        return ID;
    }

    public String getaccount() {
        return account;
    }

    public String getpassword() {
        return password;
    }

}

