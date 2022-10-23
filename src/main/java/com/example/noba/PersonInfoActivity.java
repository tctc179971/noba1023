package com.example.noba;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PersonInfoActivity extends AppCompatActivity {
    private Button btn_person_setup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        btn_person_setup = (Button) findViewById(R.id.btn_person_setup);
        btn_person_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), PersonSetup.class);
                startActivity(intent);
            }
        });
    }
}
