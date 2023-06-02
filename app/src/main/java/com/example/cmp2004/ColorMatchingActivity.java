package com.example.cmp2004;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class ColorMatchingActivity extends AppCompatActivity {

    private String[] colorNames = {"Red", "Blue", "Green", "Yellow", "Purple", "Orange", "Black",
            "White", "Cyan", "Magenta", "Lime", "Pink"};
    private int[] colorValues = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.rgb(128,
            0, 128), Color.rgb(255, 165, 0),
            Color.BLACK, Color.WHITE, Color.CYAN, Color.MAGENTA,
            Color.rgb(0, 255, 0), Color.rgb(255, 192, 203)};
    private int currentColorIndex;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_matching);

        generateNewColor();
        createColorButtons();
    }

    private void generateNewColor() {
        currentColorIndex = random.nextInt(colorNames.length);
        TextView colorNameText = findViewById(R.id.color_name_text);
        colorNameText.setText(colorNames[currentColorIndex]);
    }

    private void createColorButtons() {
        GridLayout colorGrid = findViewById(R.id.color_grid);
        for (int i = 0; i < colorNames.length; i++) {
            Button colorButton = new Button(this);
            colorButton.setId(i);
            colorButton.setBackgroundColor(colorValues[i]);
            colorButton.setText("");

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 200;
            params.height = 200;
            params.setMargins(16, 16, 16, 16);

            colorButton.setLayoutParams(params);

            int finalI = i;
            colorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (view.getId() == currentColorIndex) {
                        generateNewColor();
                        Toast.makeText(getApplicationContext(), "Correct Match! New Color is: " + colorNames[currentColorIndex], Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Match! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            colorGrid.addView(colorButton);
        }
    }
}