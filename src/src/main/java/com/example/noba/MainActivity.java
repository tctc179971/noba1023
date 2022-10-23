package com.example.noba;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public account login_acc;
    private EditText EditTextaccount;
    private EditText EditTextpassword;
    private Button btn_login;
    private Button btn_registerpage;
    private Button btn_forgetpassward;
    private Intent intent = new Intent();
    private Bundle bundle = new Bundle();
    private int UserID=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_registerpage = (Button) findViewById(R.id.btn_registerpage);
        btn_forgetpassward = (Button) findViewById(R.id.btn_forgetpassward);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText EditTextaccount = (EditText)findViewById(R.id.l_account);
                EditText EditTextpassword = (EditText)findViewById(R.id.l_password);
                new Thread(() -> {
                    login_check log=new login_check();
                    boolean checklog = log.login(EditTextaccount.getText().toString(),EditTextpassword.getText().toString());
                    int msg = 0;
                    if(checklog){
                        msg = 1;
                        UserID=log.login_ID(EditTextaccount.getText().toString());
                    }
                    checkmessage.sendEmptyMessage(msg);
                }).start();
            }
        });
        btn_registerpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),registerActivity.class));
            }
        });
        btn_forgetpassward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),registerActivity.class));
            }
        });
    }

    final Handler checkmessage = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what == 1)
            {
                intent.setClass(getApplicationContext(),PersonActivity.class);
                bundle.putInt("login_ID",UserID);
                intent.putExtras(bundle);
                Toast.makeText(getApplicationContext(),"登入成功",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"登入失敗",Toast.LENGTH_LONG).show();
            }
        }
    };
}