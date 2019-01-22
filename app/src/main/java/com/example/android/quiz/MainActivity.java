package com.example.android.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //get the edit text
EditText name,age,email;
//get text view from xml
TextView debug_text;
// get the user  name and the email
String nameOfUser,emailOfUser;
//get age of user

    String ageofuser;
//to get valid data from user
boolean valid_name=false,valid_Email=false,valid_Age=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //linke java cod to xml
        name=(EditText) findViewById(R.id.name);
        age=(EditText) findViewById(R.id.age);
        email=(EditText) findViewById(R.id.email);
        debug_text =(TextView)findViewById(R.id.debug_text_view);

         name.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

              @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {

              }
            // make sure if the user name count not lees 3
              @Override
               public void afterTextChanged(Editable s) {
                 nameOfUser=s.toString();
                 if(nameOfUser.isEmpty()||nameOfUser.length()<3){
                 debug_text.setText("invalid name please enter valid name");
                  }else {
                     debug_text.setText("welcome "+nameOfUser);
                     valid_name=true;
                          }
                         }
         });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ageofuser=s.toString();
                if(ageofuser.length()<=0||ageofuser.length()>2){
                    debug_text.setText("welcome "+nameOfUser +"\n"+"invalid age please enter age between 6 to 99");
                }else {
                    debug_text.setText("welcome "+nameOfUser +"\n"+" age "+ageofuser);
                    valid_Age=true;
                }
            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            // make sure if the user email is valid
            @Override
            public void afterTextChanged(Editable s) {
                emailOfUser=s.toString();
                if(emailOfUser.isEmpty()||!emailOfUser.contains("@")){
                    debug_text.setText("welcome "+nameOfUser +"\n"+" age "+ageofuser+"\n"+"invalid Email please enter valid Email");
                }else {
                    debug_text.setText("welcome "+nameOfUser +"\n"+" age "+ageofuser+"\n"+" Email is "+emailOfUser);
                    valid_Email=true;
                }
            }
        });

    }
//send the username and email and age to next activity
    public void send_Data_to_Quiz_Activity(View view) {

        if(valid_name&&valid_Age&&valid_Email){
            Intent myIntent=new Intent(MainActivity.this,Quiz.class);
            myIntent.putExtra("name",nameOfUser);
            myIntent.putExtra("age",ageofuser);
            myIntent.putExtra("email",emailOfUser);
            startActivity(myIntent);
        }else {
            debug_text.setText("please enter valid data");
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        nameOfUser=name.getText().toString();
        outState.putString("nameOfUser",nameOfUser);

        emailOfUser=email.getText().toString();
        outState.putString("emailOfUser",emailOfUser);

        ageofuser=age.getText().toString();
        outState.putString("ageofuser",ageofuser);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        nameOfUser=    savedInstanceState.getString("nameOfUser");
        name.setText(nameOfUser);

        emailOfUser=    savedInstanceState.getString("emailOfUser");
        email.setText(emailOfUser);

        ageofuser=    savedInstanceState.getString("ageofuser");

        age.setText(ageofuser);
    }
}
