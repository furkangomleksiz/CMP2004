package com.example.cmp2004;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class RPSActivity extends AppCompatActivity {

    private Button btnRock, btnPaper, btnScissors;
    private TextView tvResult;
    private ImageView playerChoiceImage, computerChoiceImage;
    private Animation fadeInAnimation, fadeOutAnimation;

    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rpsactivity);

        btnRock = findViewById(R.id.btnRock);
        btnPaper = findViewById(R.id.btnPaper);
        btnScissors = findViewById(R.id.btnScissors);
        tvResult = findViewById(R.id.tvResult);
        playerChoiceImage = findViewById(R.id.playerChoiceImage);
        computerChoiceImage = findViewById(R.id.computerChoiceImage);

        fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in_animation);
        fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out_animation);

        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn(0);
            }
        });

        btnPaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn(1);
            }
        });

        btnScissors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playTurn(2);
            }
        });

        calculateScreenSize();
        resizeImages();
    }

    private void calculateScreenSize() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        calculateScreenSize();
        resizeImages();
    }

    private void resizeImages() {
        int desiredWidth;
        int desiredHeight;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            desiredWidth = (int) (screenWidth * 0.4);
            desiredHeight = (int) (screenHeight * 0.6);
        } else {
            desiredWidth = (int) (screenWidth * 0.6);
            desiredHeight = (int) (screenHeight * 0.4);
        }

        playerChoiceImage.getLayoutParams().width = desiredWidth;
        playerChoiceImage.getLayoutParams().height = desiredHeight;
        computerChoiceImage.getLayoutParams().width = desiredWidth;
        computerChoiceImage.getLayoutParams().height = desiredHeight;
        playerChoiceImage.requestLayout();
        computerChoiceImage.requestLayout();
    }

    private void playTurn(int playerChoice) {
        int computerChoice = getRandomChoice();

        String playerChoiceStr = getChoiceString(playerChoice);
        String computerChoiceStr = getChoiceString(computerChoice);

        playerChoiceImage.setImageResource(getChoiceImage(playerChoice));
        computerChoiceImage.setImageResource(getChoiceImage(computerChoice));

        playerChoiceImage.startAnimation(fadeInAnimation);
        computerChoiceImage.startAnimation(fadeInAnimation);

        fadeInAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animasyon başladığında yapılacak işlemler
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Animasyon tamamlandığında yapılacak işlemler
                playerChoiceImage.setVisibility(View.VISIBLE);
                computerChoiceImage.setVisibility(View.VISIBLE);
                fadeOutAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        // Animasyon başladığında yapılacak işlemler
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        // Animasyon tamamlandığında yapılacak işlemler
                        String result = determineResult(playerChoice, computerChoice);
                        String message = "Your choice: " + playerChoiceStr +
                                "\nComputer's choice: " + computerChoiceStr +
                                "\n\n" + result;
                        tvResult.setText(message);

                        if (result.equals("You win!")) {
                            tvResult.append("\nYou chose: " + playerChoiceStr);
                        } else if (result.equals("You lose!")) {
                            tvResult.append("\nComputer chose: " + computerChoiceStr);
                        }

                        int winnerChoiceImage;
                        if (result.equals("You win!")) {
                            winnerChoiceImage = getChoiceImage(playerChoice);
                        } else if (result.equals("You lose!")) {
                            winnerChoiceImage = getChoiceImage(computerChoice);
                        } else {
                            winnerChoiceImage = R.drawable.placeholder_image;
                        }
                        playerChoiceImage.setImageResource(winnerChoiceImage);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // Animasyon tekrarlandığında yapılacak işlemler
                    }
                });
                computerChoiceImage.startAnimation(fadeOutAnimation);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animasyon tekrarlandığında yapılacak işlemler
            }
        });
        playerChoiceImage.startAnimation(fadeOutAnimation);
    }

    private int getRandomChoice() {
        Random random = new Random();
        return random.nextInt(3);
    }

    private String getChoiceString(int choice) {
        switch (choice) {
            case 0:
                return "Rock";
            case 1:
                return "Paper";
            case 2:
                return "Scissors";
            default:
                return "";
        }
    }

    private int getChoiceImage(int choice) {
        switch (choice) {
            case 0:
                return R.drawable.rock;
            case 1:
                return R.drawable.paper;
            case 2:
                return R.drawable.scissors;
            default:
                return R.drawable.placeholder_image;
        }
    }

    private String determineResult(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            return "It's a tie!";
        } else if ((playerChoice == 0 && computerChoice == 2) ||
                (playerChoice == 1 && computerChoice == 0) ||
                (playerChoice == 2 && computerChoice == 1)) {
            return "You win!";
        } else {
            return "You lose!";
        }
    }
}
