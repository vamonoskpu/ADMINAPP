package com.example.adminapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionList extends AppCompatActivity {
    static final int REQ_ADD_CONTACT =1; //requestcode
    FirebaseDatabase database;
    DatabaseReference reference;
    ArrayList<QuestionData> list;
    QuestionAdapter adapter;
    RecyclerView recyclerView;
    String answercontents;
    String title;
    String writer;
    String contents;
    String answer;





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //Answerquestion에서 답변한 내용을 받기 위해서 사용
        super.onActivityResult(requestCode, resultCode, data);
       if(requestCode == REQ_ADD_CONTACT){
           if(resultCode ==RESULT_OK){
               answercontents = data.getStringExtra("answercontents"); //답변내용받기

           }
       }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_list);





        list = new ArrayList<>();

        database=  FirebaseDatabase.getInstance(); // Firebase database 연동
        reference =database.getReference("users");// DB 테이블 연결

        recyclerView  = findViewById(R.id.questionRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


       //firebase에 데이터 가져오기
       reference.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               list.clear();
               for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                   QuestionData data = snapshot.getValue(QuestionData.class); //QusetionData class형식으로 데이터 전달받음
                   list.add(data);   //Arraylist에 데이터 저장


               }
              adapter.notifyDataSetChanged();   //데이터 변경 알림

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {   // 리사이클러뷰 클릭 시

                Intent intent = new Intent(getApplicationContext(), Answerquestion.class); //Answerquestion 화면으로 전환

                QuestionData questionData = list.get(position); //리사이클러뷰 아이템 위치 확보

                intent.putExtra("title",questionData.getTitle()); //리사이클러뷰 아이템 클릭 시 기존에 있었던 데이터를
                //Answerquestion 화면에 보여주기 위해서 데이터를 전달함
                intent.putExtra("writer",questionData.getWriter()); //작성자
                intent.putExtra("contents",questionData.getContents());  //내용
                intent.putExtra("answercontents",questionData.getAnswercontents()); //답변내용



                startActivityForResult(intent,REQ_ADD_CONTACT);
            }

            @Override
            public void onLongClick(View view, int position) { //리사이클러뷰 롱클릭 시


            }
        }));
        adapter = new QuestionAdapter(list);      //adapter를 이용해서 데이터와 view 연결
        recyclerView.setAdapter(adapter);        //recyclerview에 adapter 적용

    }


    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private ClickListener clickListener;

        public RecyclerTouchListener(Context context, final  RecyclerView recyclerView, final  ClickListener clickListener){
            this.clickListener =clickListener;
            gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                public boolean onSingleTapUp (MotionEvent e){
                    return  true;
                }

                public void onLongPress(MotionEvent e){
                    View child = recyclerView.findChildViewUnder(e.getX(),e.getY());
                    if(child != null && clickListener != null){
                        clickListener.onLongClick(child,recyclerView.getChildAdapterPosition(child));
                    }
                }
            });
        }
        @Override
        public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(),e.getY());
            if(child != null && clickListener != null && gestureDetector.onTouchEvent(e)){
                clickListener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }


        @Override
        public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }




}
