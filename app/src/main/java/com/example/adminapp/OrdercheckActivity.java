package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class OrdercheckActivity extends AppCompatActivity {

    Button plusbtn;
    Button minbtn;
    Button sendbtn;
    TextView num;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercheck);

        plusbtn = (Button)findViewById(R.id.plus);
        minbtn = (Button)findViewById(R.id.min);
        sendbtn = (Button)findViewById(R.id.send);
        num = (TextView)findViewById(R.id.count);


        plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.setText(""+count);
                count++;
            }
        });

        minbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if(count<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count = 0;
                }

                num.setText(""+count);
            }
        });
    }
}
