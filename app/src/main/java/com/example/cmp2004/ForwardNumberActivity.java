package com.example.cmp2004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class ForwardNumberActivity extends AppCompatActivity {

    Button submitButton, generateButton;
    TextView forwardNumbers;
    EditText forwardAnswer;

    Handler handler;

    int[] generatedNumbers = new int[6];
    String numberText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forward_number);

        submitButton = findViewById(R.id.submit_btn);
        generateButton = findViewById(R.id.generate_btn);
        forwardNumbers = findViewById(R.id.forward_numbers);
        forwardAnswer = findViewById(R.id.forward_answer);
        handler = new Handler();
        Random random = new Random();

        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i = 0; i < 6; i++)
                {
                    generatedNumbers[i] = random.nextInt(10);
                }
                /*for(int i = 0; i < 6; i++)
                {
                    int finalI = i;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            forwardNumbers.setText(String.valueOf(generatedNumbers[finalI]));
                        }
                    }, 2000);
                }*/

                numberText = "";
                for(int i = 0; i < 6; i++)
                {
                    numberText += String.valueOf(generatedNumbers[i]);

                    if(i != 5) {
                        numberText += " ";
                    }

                }
                forwardNumbers.setText(numberText);
                forwardAnswer.setEnabled(false);
                handler.postDelayed(new Runnable() {
                    public void run() {
                        forwardNumbers.setText("");
                        forwardAnswer.setEnabled(true);
                    }
                }, 5000);
            }
        });

       submitButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String answer = forwardAnswer.getText().toString();
               if(answer.equalsIgnoreCase(numberText))
               {
                   Toast.makeText(ForwardNumberActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
               }
               else {
                   Toast.makeText(ForwardNumberActivity.this, "Incorrect. Try again.", Toast.LENGTH_SHORT).show();
               }
               forwardAnswer.setText("");
           }
       });
    }

}