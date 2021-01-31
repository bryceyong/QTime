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
    private int rocket;
    ImageView rocketView;
    ImageView hatView;
    ImageView goatView;
    private int goat;
    private int hat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        loadRocketData();
        rocketView = (ImageView)findViewById(R.id.rocket);
        if(rocket == 1){
            rocketView.setBackgroundResource(R.drawable.rocket);
        }

        loadHatData();
        hatView = (ImageView)findViewById(R.id.hat);
        if(hat == 1){
            hatView.setBackgroundResource(R.drawable.hat);
        }

        loadGoatData();
        goatView = (ImageView)findViewById(R.id.goat);
        if(goat == 1){
            goatView.setBackgroundResource(R.drawable.goatfly);
            AnimationDrawable progressAnimation = (AnimationDrawable) goatView.getBackground();
            progressAnimation.start();
        }

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

        ImageButton piggy = (ImageButton) findViewById(R.id.piggy);
        piggy.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                openShopActivity();
            }
        });

    }



    @Override
    protected void onResume(){
        Calendar cal = Calendar.getInstance();

        loadRocketData();
        rocketView = (ImageView)findViewById(R.id.rocket);
        if(rocket == 1){
            rocketView.setBackgroundResource(R.drawable.rocket);
        } else {
            rocketView.setBackgroundResource(R.drawable.blank);
        }

        loadHatData();
        hatView = (ImageView)findViewById(R.id.hat);
        if(hat == 1){
            hatView.setBackgroundResource(R.drawable.hat);
        } else {
            rocketView.setBackgroundResource(R.drawable.blank);
        }

        loadGoatData();
        goatView = (ImageView)findViewById(R.id.goat);
        if(goat == 1){
            goatView.setBackgroundResource(R.drawable.goatfly);
            AnimationDrawable progressAnimation = (AnimationDrawable) goatView.getBackground();
            progressAnimation.start();
        } else {
            rocketView.setBackgroundResource(R.drawable.blank);
        }

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

    public void openShopActivity(){
        Intent intent = new Intent(this, ShopActivity.class);
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

    public void loadRocketData(){
        SharedPreferences sharedPreferences = getSharedPreferences("rocket", MODE_PRIVATE);
        rocket = sharedPreferences.getInt("rocket", 0);
    }

    public void saveHatData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("hat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("hat", hat);
        editor.apply();

    }

    public void loadHatData(){
        SharedPreferences sharedPreferences = getSharedPreferences("hat", MODE_PRIVATE);
        hat = sharedPreferences.getInt("hat", 0);
    }

    public void saveGoatData(){
        //SharedPreference saving
        SharedPreferences sharedPreferences = getSharedPreferences("goat", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("goat", goat);
        editor.apply();

    }

    public void loadGoatData(){
        SharedPreferences sharedPreferences = getSharedPreferences("goat", MODE_PRIVATE);
        goat = sharedPreferences.getInt("goat", 0);
    }





}