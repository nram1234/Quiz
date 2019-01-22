package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz extends AppCompatActivity {
    //String name Of User
    String nameOfUser;
    //get the intant
Intent intent;
//get edit text to get the answer of question number 1
EditText question1;
//get the RadioGroup to get the answer
RadioGroup radioGroupQ2,radioGroupQ4,radioGroupQ5;
//arry of 0 number
 int[]answer={0,0,0,0,0,0};
 //String answer question number 1
    String answer1;
    int answer_question_2,answer_question_4,answer_question_5;
    CheckBox Q3_1, Q3_2, Q3_3, Q3_4;
    //answer question number 3
    boolean answer_question_3_1,answer_question_3_2,answer_question_3_3,answer_question_3_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        //get intent
        intent=getIntent();
        //get name of user from intent from start activity
         nameOfUser=intent.getStringExtra("name");
         // make the titel of bar have name of user
        getSupportActionBar().setTitle("welcome "+nameOfUser);
        getAnswer_question3();
        //get like between java and xml
        question1 =(EditText)findViewById(R.id.answer_Q1);
        radioGroupQ2=(RadioGroup)findViewById(R.id.radioGroup_Q2);
        radioGroupQ4=(RadioGroup)findViewById(R.id.radioGroup_Q4);
        radioGroupQ5=(RadioGroup)findViewById(R.id.radioGroup_Q5);
//get what the user selection answer for question number 2
        radioGroupQ2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                answer_question_2=checkedId;
                switch (checkedId){
                    case R.id.radioButton_Q2_A1:
                        answer[1]=1;
                        break;
                    case R.id.radioButton_Q2_A2:
                        answer[1]=0;
                        break;
                    case R.id.radioButton_Q2_A3:
                        answer[1]=0;
                        break;
                    case R.id.radioButton_Q2_A4:
                        answer[1]=0;
                        break;
                }
            }
        });

//get what the user selection answer for question number 4
        radioGroupQ4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                answer_question_4=checkedId;
                switch (checkedId){
                    case R.id.radioButton_Q4_no:
                        answer[3]=0;
                        break;
                    case R.id.radioButton_Q4_yes:
                        answer[3]=1;
                        break;

                }
            }
        });
//get what the user selection answer for question number 5
        radioGroupQ5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                answer_question_5=checkedId;
                switch (checkedId){
                    case R.id.radioButton_Q5_no:
                        answer[4]=0;

                        break;
                    case R.id.radioButton_Q5_yes:
                        answer[4]=1;
                        break;

                }
            }
        });
    }
    //get what the user enter answer for question number 1
    void getAnswer_question1(){
         answer1= question1.getText().toString();
        if(answer1.equalsIgnoreCase(getString(R.string.answer_1))){
            //change the val of answer arry
            answer[0]=1;
        }else {

            answer[0]=0;
        }
    }
    //get what the user selection answer for question number 3
    void getAnswer_question3(){

          Q3_1=(CheckBox)findViewById(R.id.checkBox_A1);
        answer_question_3_1=Q3_1.isChecked();
           Q3_2=(CheckBox)findViewById(R.id.checkBox_A2);
         answer_question_3_2=Q3_2.isChecked();
           Q3_3=(CheckBox)findViewById(R.id.checkBox_A3);
         answer_question_3_3=Q3_3.isChecked();
           Q3_4=(CheckBox)findViewById(R.id.checkBox_A4);
         answer_question_3_4=Q3_4.isChecked();
        if(answer_question_3_1&&answer_question_3_2&&!answer_question_3_3&&!answer_question_3_4){
            answer[2]=1;
        }else {
            answer[2]=0;
        }

    }
//send the answer Summary of the user
    public void send_Answer(View view) {
        getAnswer_question1();
        getAnswer_question3();
//we get the user scor by sum the number of the arry if user get correct answer we replace the 0 to 1
        int score=0;
        for (int i=0;i<answer.length;i++){
            score=score+answer[i];

        }
        //toast to tell the user his score
        Toast.makeText(this, "your score is "+score, Toast.LENGTH_LONG).show();
        //get answer summary
        String message=answer_Summary();

        Intent intent = new Intent(Intent.ACTION_SENDTO);

        intent.putExtra(Intent.EXTRA_SUBJECT, nameOfUser);
        intent.putExtra(Intent.EXTRA_TEXT, message);
         startActivity(Intent.createChooser(intent, "Send email"));
     }
    String answer_Summary(){
        String ageOfUser=intent.getStringExtra("age");
        String email= intent.getStringExtra("email");
   String answerSummary="Name is : "+nameOfUser+"\n"+"Age :"+ageOfUser+"\n"+"Email : "+email;
   //get all correct answer enter by user make loop if the vall in arry 1 it correct answer and add masssg to
        //the answerSummary
for (int i =0;i<answer.length;i++){
    //the arry start from 0
    int question_number=i+1;
    if(answer[i]==1){

        answerSummary += "\n" + "the answer of question number" +question_number+ "is correct";
    }else {
        answerSummary += "\n" + "the answer of question number" +question_number+ "is not correct";

    }



}
return  answerSummary;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getAnswer_question3();
        super.onSaveInstanceState(outState);
        answer1=question1.getText().toString();
        outState.putString("answer1",answer1);
        outState.putInt("answer_question_2",answer_question_2);
        outState.putBoolean("answer_question_3_1",answer_question_3_1);
        outState.putBoolean("answer_question_3_2",answer_question_3_2);
        outState.putBoolean("answer_question_3_3",answer_question_3_3);
        outState.putBoolean("answer_question_3_4",answer_question_3_4);
        outState.putInt("answer_question_4",answer_question_4);
        outState.putInt("answer_question_5",answer_question_5);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        answer1=    savedInstanceState.getString("answer1");
        question1.setText(answer1);
        int answer_question_2=savedInstanceState.getInt("answer_question_2");
        radioGroupQ2.check(answer_question_2);
          answer_question_3_1=savedInstanceState.getBoolean("answer_question_3_1");
        Q3_1.setChecked(answer_question_3_1);

          answer_question_3_2=savedInstanceState.getBoolean("answer_question_3_2");
        Q3_2.setChecked(answer_question_3_2);

          answer_question_3_3=savedInstanceState.getBoolean("answer_question_3_3");
        Q3_3.setChecked(answer_question_3_3);

          answer_question_3_4=savedInstanceState.getBoolean("answer_question_3_4");
        Q3_4.setChecked(answer_question_3_4);

        int answer_question_4=savedInstanceState.getInt("answer_question_4");
        radioGroupQ4.check(answer_question_4);

        int answer_question_5=savedInstanceState.getInt("answer_question_5");
        radioGroupQ5.check(answer_question_5);
    }
}
