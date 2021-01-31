package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.qtime.WeekActivity.day;

public class MainActivity extends AppCompatActivity {

    public static int today;
    private int status;
    private int exp;
    private int gold;
    private String shared;
    private ArrayList<Task> list;
    ImageView failure;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadCatData();
        Log.d("status", Integer.toString(status));
        Calendar cal = Calendar.getInstance();
        today = cal.get(Calendar.DAY_OF_WEEK);

        failure = (ImageView) findViewById(R.id.failure);

        failure.setBackgroundResource(R.drawable.blank);
        loadExpData();
        if(exp == 1){
            failure.setBackgroundResource(R.drawable.failure);
            failure.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){

                    failure.setBackgroundResource(R.drawable.blank);
                    exp = 0;
                    saveExpData();
                }
            });
        }




        ImageView img = (ImageView)findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                openAfkActivity();
            }
        });
        img.setBackgroundResource(R.drawable.idle);
        if(status > 2){
            img.setBackgroundResource(R.drawable.happy);
        } else if(status < 0){
            img.setBackgroundResource(R.drawable.dying);
        }

        AnimationDrawable progressAnimation = (AnimationDrawable) img.getBackground();
        progressAnimation.start();


        ImageButton day = (ImageButton) findViewById(R.id.day);
        day.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                openWeekActivity();
            }
        });

    }



    @Override
    protected void onResume(){
        Calendar cal = Calendar.getInstance();

        loadExpData();
        if(exp == 1){
            failure.setBackgroundResource(R.drawable.failure);
            failure.setOnClickListener(new View.OnClickListener(){
                @Override

                public void onClick(View v){

                    failure.setBackgroundResource(R.drawable.blank);
                    exp = 0;
                    saveExpData();
                }
            });
        }

        ImageView img = (ImageView)findViewById(R.id.imageView);
        img.setBackgroundResource(R.drawable.idle);
        loadCatData();
        Log.d("status", Integer.toString(status));
        if(status > 2){
            img.setBackgroundResource(R.drawable.happy);
        } else if(status < 0){
            img.setBackgroundResource(R.drawable.dying);
        }

       AnimationDrawable progressAnimation = (AnimationDrawable) img.getBackground();
       progressAnimation.start();
        super.onResume();
    }


    public void openWeekActivity(){
        Intent intent = new Intent(this, WeekActivity.class);
        startActivity(intent);
    }

    public void openAfkActivity(){
        Intent intent = new Intent(this, AfkActivity.class);
        startActivity(intent);
    }

    public void saveCatData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("cat", status);
        editor.apply();

    }

    public void loadCatData(){
        SharedPreferences sharedPreferences = getSharedPreferences("status", MODE_PRIVATE);
        status = sharedPreferences.getInt("cat", 1);

    }

    public void saveExpData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("exp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("exp", exp);
        editor.apply();

    }

    public void loadExpData(){
        SharedPreferences sharedPreferences = getSharedPreferences("exp", MODE_PRIVATE);
        exp = sharedPreferences.getInt("exp", 0);

    }





}