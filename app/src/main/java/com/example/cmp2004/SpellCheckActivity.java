package com.example.cmp2004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SpellCheckActivity extends AppCompatActivity {

    private TextView scrambledWordTextView;
    private String correctWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_check);

        scrambledWordTextView = findViewById(R.id.scrambled_word);
        final EditText userAnswer = findViewById(R.id.user_answer);
        Button submitButton = findViewById(R.id.submit_button);

        generateScrambledWord();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = userAnswer.getText().toString();
                if (answer.equalsIgnoreCase(correctWord)) {
                    Toast.makeText(SpellCheckActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                    generateScrambledWord();
                } else {
                    Toast.makeText(SpellCheckActivity.this, "Incorrect. Try again.", Toast.LENGTH_SHORT).show();
                }
                userAnswer.setText("");
            }
        });
    }

    private void generateScrambledWord() {
        String[] words = {"hello", "world", "example", "android", "learning", "exercise",
                "fun", "sadness","book", "flip", "flop", "time", "running",
                "challenge", "teacher", "funny", "guess", "player", "american"};
        Random random = new Random();
        correctWord = words[random.nextInt(words.length)];
        String scrambledWord;

        do {
            List<String> letters = Arrays.asList(correctWord.split(""));
            Collections.shuffle(letters);
            StringBuilder sb = new StringBuilder(correctWord.length());
            for (String letter : letters) {
                sb.append(letter);
            }
            scrambledWord = sb.toString();
        } while (scrambledWord.equals(correctWord));

        scrambledWordTextView.setText(scrambledWord);
    }
}