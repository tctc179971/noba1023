package com.example.noba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class room{
    private int roomid;
    private String roomname;
    private String location;
    private String descript;
    private Date start_time;
    private Date dead_time;
    private int room_state;
    private int creater_id;
    private String sportType;
    private sqlcon con = new sqlcon();
    private User[] member = new User[12];

    public room(int id){
        this.roomid=id;
    }

//    public room(String roomname, String location,String descript,Date start_time,Date dead_time,int room_state,int creater_id,String sportType){
//        this.roomname=roomname;
//        this.location=location;
//        this.descript=descript;
//        this.start_time=start_time;
//        this.dead_time=dead_time;
//        this.room_state=room_state;
//        this.creater_id=creater_id;
//        this.sportType=sportType;
//    }

    public boolean roomCreater(String roomname,String location,String sportType,String descript) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date();
        String nowDate = dateFormat.format(date);
        String addroomsql = "INSERT INTO room (roomname,location, start_time,room_state,creater_id,type_id, descript) VALUES  ('" +roomname+ "',+'" + location + "','"+nowDate+"','1','1','1','" + descript + "')";
        int roomcheck=con.adddata(addroomsql);
        if(roomcheck!=0){
            return true;
        }else{
            return false;
        }

    }

    public void roomFinish() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date();
        String nowDate = dateFormat.format(date);
        String finshroomsql = "UPDATE room SET dead_time='"+nowDate+"',roomstate=0 WHERE roomid=0";
        con.alter(finshroomsql);
    }

    public boolean memberjoin(int userid) {
        if (this.checkMember()) {
            String memberaddsql = "INSERT INTO member (roomid, userid, join_state) VALUES ('"+roomid+"','"+userid+"','"+1+"')";
            if(con.adddata(memberaddsql)==0){
                return true;
            }else{
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean memberLeave(User user) {
        String memberleavesql = "UPDATE member SET join_state=0 WHERE roomid='"+roomid+"' and userid="+user.getId();
        con.alter(memberleavesql);
        return true;
    }

    public ArrayList<User> getMember(User user) {
        SimpleDateFormat dateParser = new SimpleDateFormat("MM/dd/yy HH:mm:ss");
        ArrayList<User> participate = new ArrayList<User>();
        String participatesql="Select * From member m Where roomid="+this.roomid;
        List<HashMap<String,Object>> list = new ArrayList();
        ArrayList<String> user_str=new ArrayList<String>();
        list=con.getData(participatesql);
        for(int i=0;i<list.size();i++){
//            for(int j=0;list.get(i)[j]!=null;j++) {
//                user_str.add(list.get(i)[j]);
//            }
            try {
                int userid=Integer.parseInt(user_str.get(0));
                String username=user_str.get(1);
                Date birthday = dateParser.parse(user_str.get(2));
                int phonenumber=Integer.parseInt(user_str.get(3));
                String email=user_str.get(4);
                String URL=user_str.get(5);
                Date join_time = dateParser.parse(user_str.get(6));
                User member_user=new User(userid,username,birthday,phonenumber,email,URL,join_time);
                participate.add(member_user);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return participate;
    }

    public boolean checkMember() {
        int people_limit=0;
        int now_people_num=0;
            String now_people_numsql = "Select Count(*) From member m Where m.roomid="+roomid+" and join_state=1";
            String people_numsql = "Select people_num From type t Where t.sport='"+sportType+"'";
//            now_people_num=con.getinfo("people_num",now_people_numsql);
//            people_limit=Integer.parseInt(con.getinfo(people_numsql));
//        if(now_people_num<people_limit){
        if(1<10){
            return true;
        }else{
            return false;
        }
    }

    public String getroomname(){
        return roomname;
    }
    public void setroomname(String rname) {
        this.roomname = rname;
    }
    public String getlocation(){
        return location;
    }
    public void setlocation(String rname) {
        this.roomname = rname;
    }
    public String getdescript(){
        return descript;
    }
    public Date getstarttime(){
        return start_time;
    }
    public Date getdeadtime(){
        return dead_time;
    }
}