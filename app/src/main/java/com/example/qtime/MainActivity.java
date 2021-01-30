package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton day = (ImageButton) findViewById(R.id.day);
        day.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                openWeekActivity();
            }
        });

    }

    public void openWeekActivity(){
        Intent intent = new Intent(this, WeekActivity.class);
        startActivity(intent);
    }
}