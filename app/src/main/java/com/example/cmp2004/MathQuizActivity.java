package com.example.cmp2004;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MathQuizActivity extends AppCompatActivity {

    private int answer;
    private TextView mathProblem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_quiz);

        mathProblem = findViewById(R.id.mathProblem);
        final EditText userAnswer = findViewById(R.id.userAnswer);
        Button submitAnswer = findViewById(R.id.submitAnswer);

        generateMathProblem();

        submitAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userAns = Integer.parseInt(userAnswer.getText().toString());

                if (userAns == answer) {
                    Toast.makeText(MathQuizActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    generateMathProblem();
                    userAnswer.setText("");
                } else {
                    Toast.makeText(MathQuizActivity.this, "Incorrect. Try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void generateMathProblem() {
        Random random = new Random();
        int a = random.nextInt(50);
        int b = random.nextInt(50);
        String[] operators = {"+", "-", "*", "/"};
        String operator = operators[random.nextInt(4)];

        if (operator.equals("/") && b == 0) {
            b = 1; // avoid division by zero
        }

        switch (operator) {
            case "+":
                answer = a + b;
                break;
            case "-":
                answer = a - b;
                break;
            case "*":
                answer = a * b;
                break;
            case "/":
                answer = a / b;
                break;
        }

        mathProblem.setText(String.format("%d %s %d = ?", a, operator, b));
    }
}