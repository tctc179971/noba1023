package com.example.noba;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateTeamActivity extends AppCompatActivity {

    private View creatTeam;
    private sqlcon con=new sqlcon();

    private RadioButton btnCasual;
    private RadioButton btnCompete;
    private Button btnCamera;
    private Button btnPicture;
    private Button btnSend;
    private Spinner sportType;
    private Spinner people;
    private Spinner place;
    private EditText et_roomname;
    private EditText et_context;

    private Bundle bundle = new Bundle();
    private int login_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        login_ID=(Integer)getIntent().getExtras().getInt("login_ID");
        bundle.putInt("login_ID",login_ID);

        creatTeam = findViewById(R.id.create_team);
        btnCasual = findViewById(R.id.btn_casual);
        btnCompete = findViewById(R.id.btn_competition);
        btnCamera = findViewById(R.id.btn_camera);
        btnPicture = findViewById(R.id.btn_picture);
        btnSend = findViewById(R.id.btn_send);
        sportType = findViewById(R.id.sp_type);
        sportType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String result = adapterView.getItemAtPosition(i).toString();
                System.out.println(result);
                if(result.equals("籃球")){
                    System.out.println("true");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        people = findViewById(R.id.sp_people);
        people.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String result = adapterView.getItemAtPosition(i).toString();
                System.out.println(result);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        place = findViewById(R.id.sp_place);
        place.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String result = adapterView.getItemAtPosition(i).toString();
                System.out.println(result);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        setListeners();
    }
    private void setListeners(){
        Onclick onclick = new Onclick();

        btnCasual.setOnClickListener(onclick);
        btnCompete.setOnClickListener(onclick);
        btnCamera.setOnClickListener(onclick);
        btnPicture.setOnClickListener(onclick);
        btnSend.setOnClickListener(onclick);


    }

    private class Onclick implements View.OnClickListener{
        EditText et_roomname = (EditText) findViewById(R.id.et_roomname);
        EditText et_context = (EditText) findViewById(R.id.et_context);
        Spinner place = findViewById(R.id.sp_place);

        @Override
        public void onClick(View view) {
            new Thread(() -> {
                String location= place.getSelectedItem().toString();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
                Date date = new Date();
                String nowDate = dateFormat.format(date);
                System.out.println(sportType.getSelectedItem().toString());
                String addroomsql = "INSERT INTO room (roomname,location, start_time,room_state,creater_id,type_id, descript) VALUES  ('" + et_roomname.getText().toString() + "',+'" + location + "','"+nowDate+"','1','"+login_ID+"','1','" + et_context.getText().toString() + "')";
                int checkadd =con.adddata(addroomsql);
                int msg = 0;
                if(checkadd!=0){
                    msg = 1;
                    bundle.putInt("team_ID",checkadd);
                }
                checkMessage.sendEmptyMessage(msg);
            }).start();

            switch(view.getId()){
                case R.id.btn_casual:
                    Toast.makeText(CreateTeamActivity.this,btnCasual.getText() , Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_competition:
                    Toast.makeText(CreateTeamActivity.this,btnCompete.getText() , Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btn_camera:

                    break;
                case R.id.btn_picture:

                    break;
                case R.id.btn_send:
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + view.getId());
            }
        }

    }
    final Handler checkMessage = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 1)
            {
                Toast.makeText(getApplicationContext(),"創建成功",Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),TeamActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(),"創建失敗",Toast.LENGTH_LONG).show();
            }
        }
    };

}