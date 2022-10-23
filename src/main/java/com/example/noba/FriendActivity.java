package com.example.noba;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class FriendActivity extends AppCompatActivity {
    private sqlcon con=new sqlcon();
    private EditText et_search_room;

    private Bundle bundle = new Bundle();
    private int login_ID,friend_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        login_ID=(Integer)getIntent().getExtras().getInt("login_ID");
        bundle.putInt("login_ID",login_ID);
    }
}
