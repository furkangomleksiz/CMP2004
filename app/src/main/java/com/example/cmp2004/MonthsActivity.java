package com.example.cmp2004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MonthsActivity extends AppCompatActivity {

    private int monthIndex = 0;
    private String[] months = {"January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months);

        TextView monthView = findViewById(R.id.monthView);
        Button nextButton = findViewById(R.id.nextButton);
        Button prevButton = findViewById(R.id.prevButton);

        // Initially display the first month
        monthView.setText(months[monthIndex]);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monthIndex < 11) {
                    monthIndex++;
                    monthView.setText(months[monthIndex]);
                } else{
                    Toast.makeText(MonthsActivity.this,
                            "You have reached the end of the months!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monthIndex > 0) {
                    monthIndex--;
                    monthView.setText(months[monthIndex]);
                } else{
                    Toast.makeText(MonthsActivity.this,
                            "You are at the beginning of the months!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}