package com.example.noba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class PersonActivity extends AppCompatActivity {

    private sqlcon con = new sqlcon();
    private TextView tv_user_name,tv_user_status,tv_level;
    private Button btn_searchroom;
    private Button btn_createrroom;
    private Button btn_searchrank;
    private Button btn_friends;
    private ImageButton btn_personinfo;
    private Bundle bundle = new Bundle();
    private int login_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        login_ID=(Integer)getIntent().getExtras().getInt("login_ID");
        bundle.putInt("login_ID",login_ID);

        System.out.println("現在登入ID:"+login_ID);

        btn_searchroom = (Button) findViewById(R.id.btn_searchroom);
        btn_createrroom = (Button) findViewById(R.id.btn_createrroom);
        btn_searchrank = (Button) findViewById(R.id.btn_searchrank);
        btn_friends = (Button) findViewById(R.id.btn_friends);
        btn_personinfo = (ImageButton) findViewById(R.id.btn_personinfo);

        tv_level = (TextView) findViewById(R.id.tv_level);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_user_status = (TextView) findViewById(R.id.tv_user_status);

        new Thread(() -> {
            String data_level = con.getinfo("select u.username from user u where u.ID="+login_ID);
            Log.v("OK", data_level);
            tv_level.post(() -> tv_level.setText(data_level+"/100"));

            String data_user_name = con.getinfo("select u.birthday from user u where u.ID="+login_ID);
            Log.v("OK", data_user_name);
            tv_user_name.post(() -> tv_user_name.setText(data_user_name));

            String data_user_status = con.getinfo("select u.email from user u where u.ID="+login_ID);
            Log.v("OK", data_user_status);
            tv_user_status.post(() -> tv_user_status.setText(data_user_status));
        }).start();

        btn_searchroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), TeamSearchActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_createrroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),CreateTeamActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_searchrank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),RankActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_friends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),FriendActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btn_personinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),PersonInfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}