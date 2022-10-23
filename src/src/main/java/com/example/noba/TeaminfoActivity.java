package com.example.noba;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class TeaminfoActivity extends AppCompatActivity {
    private User user;
    private room now_room;
    private Button btn_jointeam;
    private Bundle bundle = new Bundle();
    private int login_ID,team_ID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);

        login_ID=(Integer)getIntent().getExtras().getInt("login_ID");
        team_ID=(Integer)getIntent().getExtras().getInt("team_ID");
        bundle.putInt("login_ID",login_ID);
        bundle.putInt("team_ID",team_ID);

        btn_jointeam = (Button) findViewById(R.id.btn_jointeam);
        btn_jointeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                room team=new room(team_ID);
                new Thread(() -> {
                    int msg = 0;
                    if(team.memberjoin(login_ID)){
                        msg = 1;
                    }
                    checkMessage.sendEmptyMessage(msg);
                }).start();

            }
        });
    }

    final Handler checkMessage = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"加入成功",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),TeamActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"加入失敗",Toast.LENGTH_LONG).show();
            }
        }
    };
}
