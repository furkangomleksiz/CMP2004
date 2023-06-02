package com.example.cmp2004;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logoutButton;


    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.logout);
        user = auth.getCurrentUser();
        if(user == null)
        {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        Button colorMatchingGameButton = findViewById(R.id.color_matching);
        colorMatchingGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ColorMatchingActivity.class);
                startActivity(intent);
            }
        });
        Button analogClockButton = findViewById(R.id.analog_clock);
        analogClockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnalogClock.class);
                startActivity(intent);
            }
        });

        Button animalSoundButton = findViewById(R.id.animal_sounds);
        animalSoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AnimalSoundActivity.class);
                startActivity(intent);
            }
        });

        Button digitalClockButton = findViewById(R.id.digital_clock);
        digitalClockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ClockActivity.class);
                startActivity(intent);
            }
        });

        Button forwardNumberButton = findViewById(R.id.forward_numbers);
        forwardNumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForwardNumberActivity.class);
                startActivity(intent);
            }
        });

        Button mathQuizButton = findViewById(R.id.math_quiz);
        mathQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MathQuizActivity.class);
                startActivity(intent);
            }
        });

        Button monthsButton = findViewById(R.id.months);
        monthsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MonthsActivity.class);
                startActivity(intent);
            }
        });

        Button rpsButton = findViewById(R.id.rockpaperscissors);
        rpsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RPSActivity.class);
                startActivity(intent);
            }
        });

        Button spellCheckButton = findViewById(R.id.spell_check);
        spellCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SpellCheckActivity.class);
                startActivity(intent);
            }
        });

        Button tictactoeButton = findViewById(R.id.tictactoe);
        tictactoeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TicTacToeActivity.class);
                startActivity(intent);
            }
        });
    }
}