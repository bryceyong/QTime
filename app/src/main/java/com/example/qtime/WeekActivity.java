package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class WeekActivity extends AppCompatActivity {

    public static int day;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        ImageButton sunbutton = (ImageButton) findViewById(R.id.sunday);
        sunbutton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){
                day = 1;
                openDayActivity();
            }
        });

        ImageButton monbutton = (ImageButton) findViewById(R.id.monday);
        monbutton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){
                day = 2;
                openDayActivity();
            }
        });

        ImageButton tuebutton = (ImageButton) findViewById(R.id.tuesday);
        tuebutton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){
                day = 2;
                openDayActivity();
            }
        });

        ImageButton wedbutton = (ImageButton) findViewById(R.id.wednesday);
        wedbutton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){
                day = 2;
                openDayActivity();
            }
        });

        ImageButton thubutton = (ImageButton) findViewById(R.id.thursday);
        thubutton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){
                day = 2;
                openDayActivity();
            }
        });

        ImageButton fributton = (ImageButton) findViewById(R.id.friday);
        fributton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){
                day = 2;
                openDayActivity();
            }
        });

        ImageButton satbutton = (ImageButton) findViewById(R.id.saturday);
        satbutton.setOnClickListener(new View.OnClickListener(){

            @Override

            public void onClick(View v){
                day = 2;
                openDayActivity();
            }
        });

    }

    public void openDayActivity(){
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);
    }
}