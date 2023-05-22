package com.example.cmp2004;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class AnalogClock extends AppCompatActivity implements View.OnClickListener {

    TextView questionsLeftView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score = 0;
    int totalQuestion = 5;
    int currentImage = 0;
    String selectedAnswer = "";

    ImageView imageView;

    int[] images={R.drawable.analog1,
                  R.drawable.analog2,
                  R.drawable.analog3,
                  R.drawable.analog4,
                  R.drawable.analog5};

    public static String choices[][]=
            {
                    {"One past two", "Two past one", "Five past two", "Two past five"},
                    {"Thirty five past four", "Seven past five", "Five past seven", "Seven past four"},
                    {"Three past twelve","Twelve past three","Fifteen past twelve", "Thirteen past three"},
                    {"Twelve past three","Fifteen past eleven","Eleven past fifteen","Three past eleven"},
                    {"Eleven past eleven", "Fifty five past fifty five", "Fifty five past ten", "Fifty five past eleven"}
            };
    public static String answers[]=
            {
                    "Five past two",
                    "Thirty five past four",
                    "Fifteen past twelve",
                    "Fifteen past eleven",
                    "Fifty five past ten"
            };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analog_clock);

        questionsLeftView = findViewById(R.id.questionsLeft);
        imageView = findViewById(R.id.analogImage);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn= findViewById(R.id.submit_btn);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        questionsLeftView.setText("Questions left: " + totalQuestion);

        loadNewQuestion();

    }

    void loadNewQuestion()
    {
        if(totalQuestion == 0)
        {
            finishQuiz();
            return;
        }

        questionsLeftView.setText("Questions left: " + totalQuestion);
        imageView.setImageResource(images[currentImage]);
        ansA.setText(choices[currentImage][0]);
        ansB.setText(choices[currentImage][1]);
        ansC.setText(choices[currentImage][2]);
        ansD.setText(choices[currentImage][3]);
    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

    Button clickedButton = (Button) view;
    if(clickedButton.getId() == R.id.submit_btn){
        if(selectedAnswer.equals(answers[currentImage])){
            score++;
        }
        currentImage++;
        totalQuestion--;
        loadNewQuestion();

    }
    else{
        selectedAnswer = clickedButton.getText().toString();
        clickedButton.setBackgroundColor(Color.MAGENTA);
    }

    }

    void finishQuiz(){
        new AlertDialog.Builder(this).
                setTitle("Practice over").
                setMessage("You scored "+ score*20+ "%").
                setPositiveButton("Main Menu", (dialogInterface, i) -> returnToMenu()).
                setCancelable(false).
                show();
    }

    void returnToMenu(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
