package com.example.adminapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Answerquestion extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    TextView title;  //제목
    TextView writer;  //작성자
    TextView contents;   //내용
    String existingtitle;    // 기존제목
    String existingwriter;   //기존작성자
    String existingcontents; //기존내용
    EditText answercontents;
    String answercontentsvalue;
    Button answerbtn; //답변하기버튼
    Button confirmbtn ; //확인버튼
    String existinganswercontents; //답변내용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerquestion);



        answerbtn=(Button)findViewById(R.id.answerbtn);

        title = (TextView) findViewById(R.id.title1);
        writer = (TextView) findViewById(R.id.writer);
        answercontents = (EditText) findViewById(R.id.answercontents);
        contents = (TextView) findViewById(R.id.contents);


        database=  FirebaseDatabase.getInstance(); // Firebase database 연동
        reference =database.getReference("noticeboard");// DB 테이블 연결

        Bundle extras = getIntent().getExtras(); //QuestionList에서 받아온 정보를 가져옴
        if(extras != null){
            existingtitle = extras.getString("title");  //기존에 저장되어있던 제목 가져옴
            existingwriter = extras.getString("writer"); //기존에 저장되어있던 작성자 가져옴
            existingcontents = extras.getString("contents"); //기존에 저장되어있던 내용 가져옴
             existinganswercontents= extras.getString("answercontents"); //새로받아온 답변내용;


            title.setText(existingtitle);
            writer.setText(existingwriter);
            contents.setText(existingcontents);

            answercontents.setText(existinganswercontents);

        }

        answerbtn.setOnClickListener(new View.OnClickListener() { //답변하기 버튼 클릭 시
            @Override
            public void onClick(View v) {
                answercontentsvalue = answercontents.getText().toString(); //적은 답변내용 가져오기
                Map<String,Object> update = new HashMap<>();     //해쉬맵을 사용해서 데이터 값을 변경
                update.put("answercontents",answercontentsvalue);   // "answercontents" 안에 내용을 미답변에서 적은답변내용으로 변경
                update.put("answer","답변완료");
                reference.child(existingwriter).updateChildren(update);  //Firbase에 적용


                finish(); //액티비티 종료

            }
        });





    }
}
