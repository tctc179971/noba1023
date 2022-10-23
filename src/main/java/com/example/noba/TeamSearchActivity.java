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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamSearchActivity extends AppCompatActivity {
    private sqlcon con=new sqlcon();
    private EditText et_search_room;
    private Button btn_roominfo;
    private Bundle bundle = new Bundle();
    private int login_ID,team_ID;
    private List item;

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_search);

        login_ID=(Integer)getIntent().getExtras().getInt("login_ID");
        bundle.putInt("login_ID",login_ID);
        team_ID=125;
        bundle.putInt("team_ID",team_ID);

        lv = (ListView) findViewById(R.id.lv_search_team);
        new Thread(() -> {
            item=getData();
            int msg=1;
            listviewrun.sendEmptyMessage(msg);
        }).start();

        btn_roominfo = (Button) findViewById(R.id.btn_roominfo);
        btn_roominfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),TeaminfoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    private List getData() {
        List<HashMap<String, Object>> list = new ArrayList();
        list=con.getData("Select * From room r");
        return list;
    }

    final Handler listviewrun = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {

            if(msg.what == 1)
            {
                SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), item, R.layout.fragment_item_team, new String[]{"1", "2"}, new int[]{R.id.Title, R.id.Name});
                lv.setAdapter(adapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                        TextView txv = (TextView) arg1.findViewById(R.id.Title);
                        Toast.makeText(TeamSearchActivity.this, "你點擊了:" + txv.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else
            {
                Toast.makeText(getApplicationContext(),"查詢失敗",Toast.LENGTH_LONG).show();
            }
        }
    };
}