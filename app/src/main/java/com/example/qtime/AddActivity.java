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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.allyants.notifyme.NotifyMe;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;

import static com.example.qtime.WeekActivity.day;

public class AddActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private ArrayList<Task> list = new ArrayList<>();
    private Task currentTask;
    private int shour;
    private int smin;
    private int ehour;
    private int emin;
    private String task;
    private int btn = 0;
    private String shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        if(day == 2){
            shared = "monday";
        } else if(day == 3){
            shared = "tuesday";
        } else if(day == 4){
            shared = "wednesday";
        }else if(day == 5){
            shared = "thursday";
        }else if(day == 6){
            shared = "friday";
        }else if(day == 7){
            shared = "saturday";
        }else if(day == 1){
            shared = "sunday";
        }

        createNotificationChannel();
        loadData();

        ImageButton startBtn = (ImageButton) findViewById(R.id.startTime);
        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btn = 1;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        ImageButton endBtn = (ImageButton) findViewById(R.id.endTime);
        endBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btn = 2;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });


        ImageButton confirm = (ImageButton) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextInputEditText inputTask = (TextInputEditText) findViewById(R.id.inputTask);
                task = inputTask.getText().toString();
                list.add(new Task(day, shour, smin, ehour, emin, task));
                NotifyMe.Builder notifyMe = new NotifyMe.Builder(getApplicationContext());
                notifyMe.title("Qtime");
                notifyMe.content("Task");

                Calendar currentTime = Calendar.getInstance();
                Calendar alarmTime = Calendar.getInstance();

                alarmTime.set(Calendar.SECOND, 0);
                alarmTime.set(Calendar.MINUTE, smin);
                alarmTime.set(Calendar.HOUR_OF_DAY, shour);

                if(day == 2){
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                } else if(day == 3){
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
                } else if(day == 4){
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
                }else if(day == 5){
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
                }else if(day == 6){
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
                }else if(day == 7){
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
                }else if(day == 1){
                    alarmTime.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
                }

                Intent intent = new Intent(AddActivity.this,ReminderBroadcast.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this, 0, intent, 0);

                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);


                alarmManager.set(AlarmManager.RTC_WAKEUP, alarmTime.getTimeInMillis(), pendingIntent);



                if (currentTime.getTimeInMillis() <  alarmTime.getTimeInMillis()) {
                    // nothing to do - time of alarm in the future
                } else {
                    int dayDiffBetweenClosestday = (7 + alarmTime.get(Calendar.DAY_OF_WEEK) - currentTime.get(Calendar.DAY_OF_WEEK)) % 7;

                    if (dayDiffBetweenClosestday == 0) {
                        // Today is Friday, but current time after 3pm, so schedule for the next Friday
                        dayDiffBetweenClosestday = 7;
                    }

                    alarmTime.add(Calendar.DAY_OF_MONTH, dayDiffBetweenClosestday);
                    notifyMe.time(alarmTime);//The time to popup notification
                    notifyMe.build();

                }

                Log.d("task", task);
                saveData();
                openDayActivity();
            }
        });


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

    public void openDayActivity(){
        Intent intent = new Intent(this, DayActivity.class);
        startActivity(intent);
    }

}