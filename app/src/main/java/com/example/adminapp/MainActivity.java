package com.example.adminapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {



    ImageButton speeachstart;
    TextView result;
    SpeechRecognizer recognizer;
    Intent intent;
    FirebaseDatabase database;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button Receivebtn = findViewById(R.id.orderbtn);
        Button questionbtn = findViewById(R.id.questionbtn);
        final Button Ordercheckbtn = findViewById(R.id.ordercheckbtn);
        Button Menulistbtn = findViewById(R.id.menulist);

        Receivebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReceiveOrderActivity.class);
                startActivity(intent);
            }
        });

        questionbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,QuestionList.class);
                startActivity(intent);
            }
        });

        Ordercheckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,OrdercheckActivity.class);
                startActivity(intent);
            }
        });

        Menulistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MenulistActivity.class);
                startActivity(intent);
            }
        });


    }
}
