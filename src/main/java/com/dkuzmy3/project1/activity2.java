package com.dkuzmy3.project1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        setTitle("Activity 2"); // change title for fun

        Intent intent = getIntent();
        String number1 = intent.getStringExtra("number1"); // passing from main, testing ***

        final EditText numberF = findViewById(R.id.numberFieldAlt);
        numberF.setOnEditorActionListener(new TextView.OnEditorActionListener(){

            @Override   // this happens when the enter button is pressed on the soft keyboard
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if(id == EditorInfo.IME_ACTION_DONE){
                    if(numberF.getText().toString().equals("")){    // if empty, proc toast msg
                        Toast.makeText(activity2.this, "Insert Number", Toast.LENGTH_SHORT).show();
                    }
                    else{   // if not empty, get string
                        String number = numberF.getText().toString();
                        String s = "";

                        for(int i = 0; i < number.length(); i++){   // remove unnecessary stuff
                            if(number.charAt(i)=='(' || number.charAt(i)==')' || number.charAt(i)=='-' || number.charAt(i)==' ') {
                                s+="";
                            }
                            else{
                                s += number.charAt(i);  // clean string
                            }

                        }

                        String checkup = number;    // check for letters in the string, in case found, we brush that off
                        checkup = checkup.toLowerCase();
                        for(int i = 0; i < checkup.length(); i++){
                            if(checkup.charAt(i)=='a' || checkup.charAt(i)=='b' || checkup.charAt(i)=='c' || checkup.charAt(i)=='d'
                                    || checkup.charAt(i)=='e' || checkup.charAt(i)=='f' || checkup.charAt(i)=='g' || checkup.charAt(i)=='h'
                                    || checkup.charAt(i)=='m' || checkup.charAt(i)=='l' || checkup.charAt(i)=='o' || checkup.charAt(i)=='p'
                                    || checkup.charAt(i)=='q' || checkup.charAt(i)=='r' || checkup.charAt(i)=='s' || checkup.charAt(i)=='t'
                                    || checkup.charAt(i)=='u' || checkup.charAt(i)=='v' || checkup.charAt(i)=='w' || checkup.charAt(i)=='x'
                                    || checkup.charAt(i)=='y' || checkup.charAt(i)=='z' || checkup.charAt(i)=='j' || checkup.charAt(i)=='i') {
                                Intent intentReturn = new Intent();
                                setResult(RESULT_CANCELED, intentReturn);
                                finish();
                            }


                        }

                        if(testNumber(s)) { // check for phone number length
                            number = s;     // if everything is valid, pass number to main and stop
                            Intent intentReturn = new Intent();
                            intentReturn.putExtra("number", number);
                            setResult(RESULT_OK, intentReturn);
                            finish();
                        }

                        else{
                            Intent intentReturn = new Intent();
                            setResult(RESULT_CANCELED, intentReturn);
                            finish();
                        }
                    }
                    return true;
                }
                else
                    return false;
            }
        });

        Button buttonAccept = findViewById(R.id.buttonOK);  // same activity for button, as for enter on soft board
        buttonAccept.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(numberF.getText().toString().equals("")){
                    Toast.makeText(activity2.this, "Insert Number", Toast.LENGTH_SHORT).show();
                }
                else{
                    String number = numberF.getText().toString();

                    String s = "";

                    for(int i = 0; i < number.length(); i++){   // remove unnecessary stuff
                        if(number.charAt(i)=='(' || number.charAt(i)==')' || number.charAt(i)=='-' || number.charAt(i)==' ') {
                            s+="";
                        }
                        else{
                            s += number.charAt(i);
                        }

                    }

                    String checkup = number;    // check for letters in the string, in case found, we brush that off
                    checkup = checkup.toLowerCase();
                    for(int i = 0; i < checkup.length(); i++){
                        if(checkup.charAt(i)=='a' || checkup.charAt(i)=='b' || checkup.charAt(i)=='c' || checkup.charAt(i)=='d'
                                || checkup.charAt(i)=='e' || checkup.charAt(i)=='f' || checkup.charAt(i)=='g' || checkup.charAt(i)=='h'
                                || checkup.charAt(i)=='m' || checkup.charAt(i)=='l' || checkup.charAt(i)=='o' || checkup.charAt(i)=='p'
                                || checkup.charAt(i)=='q' || checkup.charAt(i)=='r' || checkup.charAt(i)=='s' || checkup.charAt(i)=='t'
                                || checkup.charAt(i)=='u' || checkup.charAt(i)=='v' || checkup.charAt(i)=='w' || checkup.charAt(i)=='x'
                                || checkup.charAt(i)=='y' || checkup.charAt(i)=='z' || checkup.charAt(i)=='j' || checkup.charAt(i)=='i') {
                            Intent intentReturn = new Intent();
                            setResult(RESULT_CANCELED, intentReturn);
                            finish();
                        }


                    }

                    if(testNumber(s)) { // check for phone number length
                        number = s;
                        Intent intentReturn = new Intent();
                        intentReturn.putExtra("number", number);
                        setResult(RESULT_OK, intentReturn);
                        finish();
                    }

                    else{
                        Intent intentReturn = new Intent();
                        setResult(RESULT_CANCELED, intentReturn);
                        finish();
                    }
                }
            }
        });
    }

    private boolean testNumber(String num){
        boolean check = true;
        if(num.length()!= 10) return false;
        Log.i("Number: ", num);
        //try{Integer.parseInt(num); return true;}
        //catch(NumberFormatException E){return false;}

        return check;
    }
}