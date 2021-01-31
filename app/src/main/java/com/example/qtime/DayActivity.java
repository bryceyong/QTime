package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.allyants.notifyme.NotifyMe;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static com.example.qtime.WeekActivity.day;

public class DayActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManger;
    private String shared;



    private ArrayList<Task> list;
    private Task currentTask;
    private int shour;
    private int smin;
    private int ehour;
    private int emin;
    private String task;
    private int btn = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        ImageView dayView = (ImageView)findViewById(R.id.header);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(this);
        mAdapter = new DayAdapter(list);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();


        if(day == 2){
            shared = "monday";
            dayView.setBackgroundResource(R.drawable.monday);
        } else if(day == 3){
            shared = "tuesday";
            dayView.setBackgroundResource(R.drawable.tuesday);
        } else if(day == 4){
            shared = "wednesday";
            dayView.setBackgroundResource(R.drawable.wednesday);
        }else if(day == 5){
            shared = "thursday";
            dayView.setBackgroundResource(R.drawable.thursday);
        }else if(day == 6){
            shared = "friday";
            dayView.setBackgroundResource(R.drawable.friday);
        }else if(day == 7){
            shared = "saturday";
            dayView.setBackgroundResource(R.drawable.saturday);
        }else if(day == 1){
            shared = "sunday";
            dayView.setBackgroundResource(R.drawable.sunday);
        }



        createNotificationChannel();
        loadData();

        ImageButton deleteBtn = (ImageButton) findViewById(R.id.delete);
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.size() != 0){
                    int last = list.size() - 1;
                    list.remove(last);
                    saveData();
                    mAdapter.notifyDataSetChanged();
                }
            }
        });



        ImageButton confirm = (ImageButton) findViewById(R.id.add);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAddActivity();


            }
        });


        //set times for Monday

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(this);
        mAdapter = new DayAdapter(list);
        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);
    }

    protected void onResume() {
        mAdapter.notifyDataSetChanged();
        super.onResume();
    }

    private void createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "QtimeReminderChannel";
            String description = "Channel for Qtime Reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("notifyQtime", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences(shared, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(shared, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<Task>>() {}.getType();
        list = gson.fromJson(json, type);

        if(list == null){
            list = new ArrayList<>();
        }
    }




    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        if(btn == 1){
            shour = hourOfDay;
            smin = minute;
            TextView startTxt = (TextView) findViewById(R.id.startTxt);
            startTxt.setText(shour +": "+ smin);
        } else if(btn == 2){
            ehour = hourOfDay;
            emin = minute;
            TextView startTxt = (TextView) findViewById(R.id.endTxt);
            startTxt.setText(ehour +": "+ emin);
        }

    }





    public void openAddActivity(){
        Intent intent = new Intent(this, AddActivity.class);
        startActivity(intent);
    }

}