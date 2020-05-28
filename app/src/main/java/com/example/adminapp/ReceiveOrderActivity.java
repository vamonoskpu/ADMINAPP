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

//import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class ReceiveOrderActivity extends AppCompatActivity {
    String uid;
    FirebaseDatabase database;
    DatabaseReference reference, userText_ref;
    ImageButton speeachstart;



    Button ordercomplete;
    Button plusbtn0;
    Button plusbtn1;
    Button plusbtn2;
    Button plusbtn3;
    Button plusbtn4;
    Button plusbtn5;
    Button plusbtn6;
    Button plusbtn7;
    Button plusbtn8;
    Button plusbtn9;
    Button minbtn0;
    Button minbtn1;
    Button minbtn2;
    Button minbtn3;
    Button minbtn4;
    Button minbtn5;
    Button minbtn6;
    Button minbtn7;
    Button minbtn8;
    Button minbtn9;
    TextView num0;
    TextView num1;
    TextView num2;
    TextView num3;
    TextView num4;
    TextView num5;
    TextView num6;
    TextView num7;
    TextView num8;
    TextView num9;
    TextView admin;
    TextView textView;



    TextView num;
    int count0=0;
    int count1=0;
    int count2=0;
    int count3=0;
    int count4=0;
    int count5=0;
    int count6=0;
    int count7=0;
    int count8=0;
    int count9=0;

    Button minbtn;

    DatabaseReference databaseReference;
    DatabaseReference databaseReference2;


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
        plusbtn8= findViewById(R.id.plus8);
        plusbtn9= findViewById(R.id.plus9);
        minbtn0 = findViewById(R.id.min0);
        minbtn1= findViewById(R.id.min1);
        minbtn2 =findViewById(R.id.min2);
        minbtn3 = findViewById(R.id.min3);
        minbtn4 = findViewById(R.id.min4);
        minbtn5  = findViewById(R.id.min5);
        minbtn6 = findViewById(R.id.min6);
        minbtn7 = findViewById(R.id.min7);
        minbtn8 = findViewById(R.id.min8);
        minbtn9 = findViewById(R.id.min9);
        num0= findViewById(R.id.count0);
        num1 = findViewById(R.id.count1);
        num2 = findViewById(R.id.count2);
        num3 = findViewById(R.id.count3);
        num4 = findViewById(R.id.count4);
        num5 =findViewById(R.id.count5);
        num6 = findViewById(R.id.count6);
        num7 = findViewById(R.id.count7);
        num8 = findViewById(R.id.count8);
        num9 = findViewById(R.id.count9);
        admin = findViewById(R.id.examtext);
        textView = findViewById(R.id.textView4);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference("Usermenu");
        databaseReference2 =database.getReference();
        userText_ref= database.getReference("8VZm145EEvgpOKfyjWzghGP4zji2");
       // plusbtn = (Button)findViewById(R.id.plus);
        reference =database.getReference();
        userText_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                MenuData2 menuData2 = dataSnapshot.getValue(MenuData2.class);
                textView.setText(menuData2.getUsermenu());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        payment = findViewById(R.id.payment);

               Integer[] btnIDs = { R.id.plus0,R.id.plus1,R.id.plus2,R.id.plus3,R.id.plus4,R.id.plus4,R.id.plus5,R.id.plus6,R.id.plus7,R.id.plus8,R.id.plus9};



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

                TextDB textdb= new TextDB(" "+rs[0]);
                admin.setText(" "+rs[0]);
                databaseReference.setValue(menuData); //Firebase에 데이터 넣기

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



        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int payment1 = dataSnapshot.child("payment").getValue(int.class);    //결제수단

                if(payment1 ==2131165291){
                    payment.setImageResource(R.drawable.card);
                    payment.setVisibility(View.VISIBLE);
                }else if(payment1 ==2131165347){
                    payment.setImageResource(R.drawable.money);
                    payment.setVisibility(View.VISIBLE);
                }

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
        //수정

        plusbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num8.setText(""+count7);
                count8++;
            }
        });
        minbtn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count8--;
                if(count8<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count8 = 0;
                }

                num8.setText(""+count8);
            }
        });
        plusbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num9.setText(""+count9);
                count9++;
            }
        });
        minbtn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count9--;
                if(count9<0){
                    Toast.makeText(getApplicationContext(),"못해",Toast.LENGTH_SHORT).show();
                    count9 = 0;
                }

                num9.setText(""+count9);
            }
        });



        ordercomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count0 >0){

                    reference.child("Menucount").child("americano").setValue(count0-1);
                }if(count1>0){
                    reference.child("Menucount").child("cafemocha").setValue(count1-1);
                } if(count2>0){
                    reference.child("Menucount").child("cafelatte").setValue(count2-1);
                } if(count3>0){
                    reference.child("Menucount").child("caramelmacchiato").setValue(count3-1);
                } if(count4>0){
                    reference.child("Menucount").child("coldbrewcoffee").setValue(count4-1);
                } if(count5>0){
                    reference.child("Menucount").child("frappuccino").setValue(count5-1);
                } if(count6>0){
                    reference.child("Menucount").child("hotchocolate").setValue(count6-1);
                }if(count7>0){
                    reference.child("Menucount").child("smoothie").setValue(count7-1);
                }if(count8>0){
                    reference.child("Menucount").child("milk").setValue(count8-1);
                }if(count9>0){
                    reference.child("Menucount").child("vanillaflatwhite").setValue(count9-1);
                }
                Toast.makeText(getApplicationContext(),"주문확인으로 넘어갑니다.",Toast.LENGTH_SHORT).show();
                
                Intent intent = new  Intent(ReceiveOrderActivity.this,OrdercheckActivity.class);
                startActivity(intent);
            }
        });
    }

}
