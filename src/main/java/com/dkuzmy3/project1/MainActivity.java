package com.dkuzmy3.project1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // defining objects
    private boolean numCheck = false;
    private EditText NFM;
    private TextView invalid;
    private Button InsertButton;
    private Button DialButton;
    private String result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // assigning objects to respective id items
        NFM = findViewById(R.id.numberFieldMain);
        invalid = findViewById(R.id.invalid);
        InsertButton = findViewById(R.id.button1);
        DialButton = findViewById(R.id.button2);
        // on button INSERT, switch activity
        InsertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, activity2.class);
                intent.putExtra("number1", "");
                startActivityForResult(intent, 1);
            }
        });
        // on button DIAL, transfer the number to phone's dialing app
        DialButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(numCheck) {
                    Uri number = Uri.parse("tel:" + result);
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
                    startActivity(callIntent);
                }

                else    // when the number is not valid, toast msg
                    Toast.makeText(MainActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    // retrieving info from activity2
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        NFM.setText("");
        invalid.setText("");
        numCheck = false;

        if(requestCode == 1){
            if(resultCode == RESULT_OK){    // when it's successful, add number to the textfield
                result = data.getStringExtra("number");
                numCheck = true;
                NFM.setText(""+result);
            }
            if(resultCode == RESULT_CANCELED){  // proc invalid number if invalid
                numCheck = false;
                NFM.setText("Invalid Number");
            }
        }
    }
}