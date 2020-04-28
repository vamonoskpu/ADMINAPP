package com.example.adminapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReceiveOrderActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    ImageButton speeachstart;
    TextView tv;


    Button ordercomplete;
    Button plusbtn0;
    Button plusbtn1;
    Button plusbtn2;
    Button plusbtn3;
    Button plusbtn4;
    Button plusbtn5;
    Button plusbtn6;
    Button plusbtn7;
    Button minbtn0;
    Button minbtn1;
    Button minbtn2;
    Button minbtn3;
    Button minbtn4;
    Button minbtn5;
    Button minbtn6;
    Button minbtn7;
    TextView num0;
    TextView num1;
    TextView num2;
    TextView num3;
    TextView num4;
    TextView num5;
    TextView num6;
    TextView num7;



    TextView num;
    int count0=0;
    int count1=0;
    int count2=0;
    int count3=0;
    int count4=0;
    int count5=0;
    int count6=0;
    int count7=0;

    Button minbtn;

    DatabaseReference databaseReference;

    SpeechRecognizer recognizer;
    Intent intent;
    ImageView payment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_order);

        speeachstart= findViewById(R.id.SpeeachStart);
        plusbtn0 = findViewById(R.id.plus0);
        plusbtn1 = findViewById(R.id.plus1);
        plusbtn2 = findViewById(R.id.plus2);
        plusbtn3 = findViewById(R.id.plus3);
        plusbtn4 = findViewById(R.id.plus4);
        plusbtn5 = findViewById(R.id.plus5);
        plusbtn6 = findViewById(R.id.plus6);
        plusbtn7= findViewById(R.id.plus7);
        minbtn0 = findViewById(R.id.min0);
        minbtn1= findViewById(R.id.min1);
        minbtn2 =findViewById(R.id.min2);
        minbtn3 = findViewById(R.id.min3);
        minbtn4 = findViewById(R.id.min4);
        minbtn5  = findViewById(R.id.min5);
        minbtn6 = findViewById(R.id.min6);
        minbtn7 = findViewById(R.id.min7);
        num0= findViewById(R.id.count0);
        num1 = findViewById(R.id.count1);
        num2 = findViewById(R.id.count2);
        num3 = findViewById(R.id.count3);
        num4 = findViewById(R.id.count4);
        num5 =findViewById(R.id.count5);
        num6 = findViewById(R.id.count6);
        num7 = findViewById(R.id.count7);
        tv = findViewById(R.id.examtext);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Usermenu");
       // plusbtn = (Button)findViewById(R.id.plus);
        reference =database.getReference();
        payment = findViewById(R.id.payment);

               Integer[] btnIDs = { R.id.plus0,R.id.plus1,R.id.plus2,R.id.plus3,R.id.plus4,R.id.plus4,R.id.plus5,R.id.plus6,R.id.plus7};



        ordercomplete = (Button)findViewById(R.id.complete);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 5);
            Toast.makeText(getApplicationContext(), "음성인식 권한을 허용해주세요", Toast.LENGTH_SHORT).show();

        }

        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        recognizer = SpeechRecognizer.createSpeechRecognizer(this);


        recognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle params) {
                //사용자가 말하기 시작할 때 호출
                Toast.makeText(getApplicationContext(),"음성인식을 시작합니다",Toast.LENGTH_SHORT).show();
                speeachstart.setImageResource(R.drawable.ic_mic_black_24dp);

            }

            @Override
            public void onBeginningOfSpeech() {


            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {
                //음성인식 종료시
                Toast.makeText(getApplicationContext(),"음성인식 종료",Toast.LENGTH_SHORT).show();
                speeachstart.setImageResource(R.drawable.ic_mic_none_black_24dp);
            }

            @Override
            public void onError(int error) {
                //  recognizer.destroy();
            }

            @Override
            public void onResults(Bundle results) {
                String arry =" ";
                arry = SpeechRecognizer.RESULTS_RECOGNITION;
                ArrayList<String> mResult = results.getStringArrayList(arry);
                String[] rs = new String[mResult.size()];
                mResult.toArray(rs);

                MenuData menuData = new MenuData(rs[0]);
                // recognizer.destroy();

                //TextDB textdb= new TextDB(" "+rs[0]);
                databaseReference.setValue(menuData);
                tv.setText(rs[0]);
                //Firebase에 데이터 넣기

            }

            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        speeachstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recognizer.startListening(intent);
            }
        });



       databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
           /*     int payment1 = dataSnapshot.child("payment").getValue(int.class);    //결제수단

                if(payment1 ==2131165295){
                    payment.setImageResource(R.drawable.card);
                    payment.setVisibility(View.VISIBLE);
                }else if(payment1 ==2131165355){
                    payment.setImageResource(R.drawable.money);
                    payment.setVisibility(View.VISIBLE);
                }
                */


            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       plusbtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num0.setText(""+count0);
                count0++;
            }
        });

        minbtn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count0--;
                if(count0<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count0 = 0;
                }

                num0.setText(""+count0);
            }
        });

        plusbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num1.setText(""+count1);
                count1++;
            }
        });
        minbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count1--;
                if(count1<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count1 = 0;
                }

                num1.setText(""+count1);
            }
        });

        plusbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num2.setText(""+count2);
                count2++;
            }
        });
        minbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count2--;
                if(count2<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count2 = 0;
                }

                num2.setText(""+count2);
            }
        });
        plusbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num3.setText(""+count3);
                count3++;
            }
        });
        minbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count3--;
                if(count3<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count3 = 0;
                }

                num3.setText(""+count3);
            }
        });
        plusbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num4.setText(""+count4);
                count4++;
            }
        });
        minbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count4--;
                if(count4<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count4 = 0;
                }

                num4.setText(""+count4);
            }
        });
        plusbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num5.setText(""+count5);
                count5++;
            }
        });
        minbtn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count5--;
                if(count5<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count5 = 0;
                }

                num5.setText(""+count5);
            }
        });
        plusbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num6.setText(""+count6);
                count6++;
            }
        });
        minbtn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count6--;
                if(count6<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count6 = 0;
                }

                num6.setText(""+count6);
            }
        });
        plusbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num7.setText(""+count7);
                count7++;
            }
        });
        minbtn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count7--;
                if(count7<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count7 = 0;
                }

                num7.setText(""+count7);
            }
        });

        ordercomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count0 >0){

                    reference.child("Menucount").child("americano").setValue(count0-1);
                }if(count1>0){
                    reference.child("Menucount").child("cafelatte").setValue(count1-1);
                } if(count2>0){
                    reference.child("Menucount").child("cafemocha").setValue(count2-1);
                } if(count3>0){
                    reference.child("Menucount").child("caramelmacchiato").setValue(count3-1);
                } if(count4>0){
                    reference.child("Menucount").child("espresso").setValue(count4-1);
                } if(count5>0){
                    reference.child("Menucount").child("frappuccino").setValue(count5-1);
                } if(count6>0){
                reference.child("Menucount").child("hotchocolate").setValue(count6-1);
            }if(count7>0){
                    reference.child("Menucount").child("smoothie").setValue(count7-1);
                }


            }
        });






    }
}
