package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WeekActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week);

        Button button = (Button) findViewById(R.id.monday);
        button.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                openDayActivity();
            }
        });
    }

    public void openDayActivity(){
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);
    }
}