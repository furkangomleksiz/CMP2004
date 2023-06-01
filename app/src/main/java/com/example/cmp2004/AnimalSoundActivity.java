package com.example.cmp2004;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class AnimalSoundActivity extends AppCompatActivity {

    ImageButton catButton;
    ImageButton cowButton;
    ImageButton chickenButton;
    ImageButton dogButton;
    ImageButton frogButton;
    ImageButton pigButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_sound);

        catButton = findViewById(R.id.cat_btn);
        cowButton = findViewById(R.id.cow_btn);
        chickenButton = findViewById(R.id.chicken_btn);
        dogButton = findViewById(R.id.dog_btn);
        frogButton = findViewById(R.id.frog_btn);
        pigButton = findViewById(R.id.pig_btn);

        catButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(AnimalSoundActivity.this, R.raw.cat_sound);
                mediaPlayer.start();
            }
        });

        dogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(AnimalSoundActivity.this, R.raw.dog_sound);
                mediaPlayer.start();
            }
        });

        cowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(AnimalSoundActivity.this, R.raw.cow_sound);
                mediaPlayer.start();
            }
        });

        chickenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(AnimalSoundActivity.this, R.raw.chicken_sound);
                mediaPlayer.start();
            }
        });

        frogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(AnimalSoundActivity.this, R.raw.frog_sound);
                mediaPlayer.start();
            }
        });

        pigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer = MediaPlayer.create(AnimalSoundActivity.this, R.raw.pig_sound);
                mediaPlayer.start();
            }
        });

    }
}