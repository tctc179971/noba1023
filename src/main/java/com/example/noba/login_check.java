package com.example.noba;


public class login_check {
    private sqlcon con=new sqlcon();


    public boolean login(String account,String password){

        String checksql = "select a.account from accounts a Where a.account='"+account+"' and a.password='"+password+"'";
        if(con.infocheck(checksql)){
            return true;
        }
        return false;
    }

    public int login_ID(String account){
        int log_ID;
        String grtIDsql = "select a.ID from accounts a Where a.account='"+account+"'";
        log_ID=Integer.parseInt(con.getinfo(grtIDsql));
        return log_ID;
    }

    public boolean register(account acc){

        String addaccount = "insert into accounts(account,password) values ('"+acc.getaccount()+"','"+acc.getpassword()+"')";
        if(con.adddata(addaccount)!=0){
            return true;
        }
        return false;
    }

    public boolean checkRegister(String account){
        String findaccsql = "select a.account from accounts a where a.account ='"+account+"'";
        boolean findacc=con.infocheck(findaccsql);
        if(findacc==true){
            return false;
        }else{
            return true;
        }
//        try {
//            PreparedStatement pst=con.prepareStatement(sql);
//            pst.setString(1,name);
//            ResultSet rs = pst.executeQuery();
//            while (rs.next()){
//                int id = rs.getInt(0);
//                String namedb = rs.getString(1);
//                String username = rs.getString(2);
//                String passworddb  = rs.getString(3);
//                int age = rs.getInt(4);
//                String phone = rs.getString(5);
//                user = new User(id,namedb,username,passworddb,age,phone);
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }finally {
//            JDBCUtils.close(con);
//        }
    }


}
