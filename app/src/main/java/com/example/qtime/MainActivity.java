package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public int today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar cal = Calendar.getInstance();
        today = cal.get(Calendar.DAY_OF_WEEK);
        Log.d("day", Integer.toString(today));

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