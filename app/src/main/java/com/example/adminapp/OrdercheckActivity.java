package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class OrdercheckActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;

    Button ordercomplete;
    Button plusbtn;
    Button minbtn;
    Button sendbtn;
    TextView num;
    int count=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordercheck);


        ordercomplete = (Button)findViewById(R.id.ordercomplete);


        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Usermenu");

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

        ordercomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child("usercount").setValue(count+"");
                reference.child("ordercheck").setValue("준비 완료");

            }
        });

    }
}
