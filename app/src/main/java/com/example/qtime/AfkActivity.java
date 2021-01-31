package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import static android.net.sip.SipErrorCode.TIME_OUT;
import static com.example.qtime.MainActivity.today;

public class AfkActivity extends AppCompatActivity {

    private ArrayList<Task> list;
    private String shared;
    Calendar cS = Calendar.getInstance();
    Calendar cE = Calendar.getInstance();
    private int flag = 0;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliseconds = 0;
    private boolean timerRunning;
    String task;
    ImageView success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afk);

        success = (ImageView)findViewById(R.id.success);
        success.setVisibility(View.GONE);
        TextView taskbar = (TextView)findViewById(R.id.taskbar);


        ImageView img = (ImageView)findViewById(R.id.run);
        img.setBackgroundResource(R.drawable.running);
        AnimationDrawable progressAnimation = (AnimationDrawable) img.getBackground();
        progressAnimation.start();

        if(today == 2){
            shared = "monday";
        } else if(today == 3){
            shared = "tuesday";
        } else if(today == 4){
            shared = "wednesday";
        }else if(today == 5){
            shared = "thursday";
        }else if(today == 6){
            shared = "friday";
        }else if(today == 7){
            shared = "saturday";
        }else if(today == 1){
            shared = "sunday";
        }
        loadTimeData();


        for(Task x: list){
            Log.d("task", x.getTask());
            Log.d("time", Integer.toString(x.getStartHour()));
            Log.d("time", Integer.toString(x.getEndHour()));
            Log.d("time", Integer.toString(x.getStartMin()));
            Log.d("time", Integer.toString(x.getEndMin()));
            cS.set(Calendar.SECOND, 0);
            cS.set(Calendar.MINUTE, x.getStartMin());
            cS.set(Calendar.HOUR_OF_DAY, x.getStartHour());
            long startTime = cS.getTimeInMillis();

            cE.set(Calendar.SECOND, 0);
            cE.set(Calendar.MINUTE, x.getEndMin());
            cE.set(Calendar.HOUR_OF_DAY, x.getEndHour());
            long endTime = cE.getTimeInMillis();

            long currentTime = System.currentTimeMillis();
            Log.d("time", Long.toString(currentTime));
            Log.d("time", Long.toString(startTime));
            Log.d("time", Long.toString(endTime));

            if(startTime <= currentTime && currentTime <= endTime){
                flag = 1;
                timeLeftInMilliseconds = endTime - startTime;
                task = x.getTask();

                break;
            }
        }

        if(flag == 1){
            startTimer();
            taskbar.setText(task);
            Log.d("time", "in Between");
        }


    }

    public void startTimer(){
        countDownTimer = new CountDownTimer(timeLeftInMilliseconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds = l;
                updateTimer();
            }


            @Override
            public void onFinish() {

                success.setVisibility(View.VISIBLE);
                Log.d("sucess", "sucess");


            }
        }.start();

    }

    public void stopTimer(){
        countDownTimer.cancel();
    }

    public void updateTimer(){
        int minutes = (int) timeLeftInMilliseconds / 60000;
        int seconds = (int) timeLeftInMilliseconds % 60000 / 1000;
        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if(seconds <10) timeLeftText += "0";
        timeLeftText += seconds;

        TextView countdownText = (TextView)findViewById(R.id.timer);
        countdownText.setText(timeLeftText);


    }



    private void loadTimeData(){
        SharedPreferences sharedPreferences = getSharedPreferences(shared, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        list = gson.fromJson(json, type);

        if(list == null){
            list = new ArrayList<>();
        }
    }
}
