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

public class registerActivity extends AppCompatActivity {
    EditText r_account = null;
    EditText r_password = null;
    EditText ckr_password = null;
    private Button button_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        r_account = findViewById(R.id.r_account);
        r_password = findViewById(R.id.r_password);
        ckr_password = findViewById(R.id.ckr_password);
        button_register = (Button) findViewById(R.id.button_register);
        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = r_account.getText().toString();
                String password = r_password.getText().toString();
                String ckpassword = ckr_password.getText().toString();
                if(account.length() < 2 || password.length() < 2){
                    Toast.makeText(getApplicationContext(),"輸入資訊不符合要求請重新輸入",Toast.LENGTH_LONG).show();
                }else {
                    if(password.equals(ckpassword)){
                        new Thread(() -> {
                            int msg=reg(account,password);
                            checkmessage.sendEmptyMessage(msg);
                        }).start();
                    }else{
                        Toast.makeText(getApplicationContext(),"檢查密碼不同",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    synchronized private static int reg(String account,String password){
        login_check reg=new login_check();
        boolean ck_acc = reg.checkRegister(account);
        account new_acc=new account(account,password);
        int msg=0;
        if (ck_acc==false) {
            msg = 1;
        }else{
            if (reg.register(new_acc)) {
                msg = 2;
            }
        }
        return msg;
    }


    final Handler checkmessage = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0)
            {
                Toast.makeText(getApplicationContext(),"註冊失敗",Toast.LENGTH_LONG).show();
            }
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"該賬號已經存在，請換一個賬號",Toast.LENGTH_LONG).show();
            }
            if(msg.what == 2)
            {
                Toast.makeText(getApplicationContext(),"註冊成功",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        }
    };
}
