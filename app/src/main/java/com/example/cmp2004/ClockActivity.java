package com.example.cmp2004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class ClockActivity extends AppCompatActivity {

    // Initialize variables for point system and time
    private int points = 0;
    private int hours = 0;
    private int minutes = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        TextView pointsView = findViewById(R.id.pointsView);
        TextView digitalClock = findViewById(R.id.digitalClock);
        Button quizButton = findViewById(R.id.quizButton);
        EditText quizAnswer = findViewById(R.id.quizAnswer);

        pointsView.setText("Points: " + points);

        // Generate random time and display on digital clock
        Random random = new Random();
        updateRandomTime(random, digitalClock);

        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = quizAnswer.getText().toString();
                // Check answer against various expected answer formats
                String expectedAnswer1 = String.format("%d hours and %d minutes past", hours, minutes);
                String expectedAnswer2 = String.format("%d hours %d minutes past", hours, minutes);
                String expectedAnswer3 = String.format("%d h and %d min past", hours, minutes);
                String expectedAnswer4 = String.format("%d h %d min past", hours, minutes);

                if (hours == 0) {
                    // Special case for midnight
                    expectedAnswer1 = expectedAnswer2 = String.format("midnight and %d minutes past", minutes);
                    expectedAnswer3 = expectedAnswer4 = String.format("midnight %d min past", minutes);
                }

                if (answer.equals(expectedAnswer1) || answer.equals(expectedAnswer2) || answer.equals(expectedAnswer3) || answer.equals(expectedAnswer4)) {
                    points++;
                    pointsView.setText("Points: " + points);
                    Toast.makeText(ClockActivity.this, "Correct answer!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ClockActivity.this, "Incorrect answer!", Toast.LENGTH_SHORT).show();
                }

                // Generate a new random time for the next question
                updateRandomTime(random, digitalClock);

                // Clear the answer field for the next question
                quizAnswer.setText("");
            }
        });
    }

    private void updateRandomTime(Random random, TextView digitalClock) {
        hours = random.nextInt(24);
        minutes = random.nextInt(60);
        digitalClock.setText(String.format("%02d:%02d", hours, minutes));
    }
}