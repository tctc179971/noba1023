package com.example.noba;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class sqlcon {

    String mysql_ip = "140.127.220.73";
    int mysql_port = 3306;
    String db_name = "NobaJosport";
    String url = "jdbc:mysql://"+mysql_ip+":"+mysql_port+"/"+db_name+"?useUnicode=true&characterEncoding=UTF-8";
    String db_user = "root";
    String db_password = "RBS%bf*5?rzV";



    public void run() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Log.v("DB","加載驅動成功");
        }catch( ClassNotFoundException e) {
            Log.e("DB","加載驅動失敗");
            return;
        }

        // 連接資料庫
        try {
            DriverManager.getConnection(url,db_user,db_password);
            Log.v("DB","遠端連接成功");
        }catch(SQLException e) {
            Log.e("DB","遠端連接失敗");
            Log.e("DB", e.toString());
        }
    }

    public int adddata(String sqlstr) {
        int check=0;
        int newid=0;
            run();
            try {
                Connection con = DriverManager.getConnection(url, db_user, db_password);
                String sql = sqlstr;
                Statement st = con.createStatement();
                check=st.executeUpdate(sql);
                ResultSet rs = st.executeQuery("SELECT last_insert_id()");
                if (rs.next())
                {
                    newid = rs.getInt(1);
                }
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(check>0){
                return newid;
            }
            return 0;
    }

    public void alter(String sqlstr) {
            run();
            try {
                Connection con = DriverManager.getConnection(url, db_user, db_password);
                String sql = sqlstr;
                Statement st = con.createStatement();
                st.execute(sql);
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public List<HashMap<String,Object>> getData(String sqlstr) {
        List<HashMap<String,Object>> list = new ArrayList();
            run();
            try {
                Connection con = DriverManager.getConnection(url, db_user, db_password);
                String sql = sqlstr;
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next())
                {
                    HashMap<String,Object> map = new HashMap<>();
                    for(int i=1;rs.getString(i)!=null;i++){
//                        data_str[i]=rs.getString(i);
                        map.put(String.valueOf(i),rs.getString(i));
                    }
//                    Data.add(data_str);
                    list.add(map);
                }
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return list;
    }

    public String getinfo(String sqlstr) {
        String data_info="";
            run();
            try {
                Connection con = DriverManager.getConnection(url, db_user, db_password);
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(sqlstr);
                while (rs.next())
                {
                    data_info = rs.getString(1);
                }
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return data_info;
    }
    public boolean infocheck(String sqlstr) {
        run();
        try {
            Connection con = DriverManager.getConnection(url, db_user, db_password);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sqlstr);

            if(rs.next()){
                return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
