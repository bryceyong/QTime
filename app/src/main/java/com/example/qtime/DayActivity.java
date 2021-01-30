package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class DayActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManger;

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

        loadData();
        Button startBtn = (Button) findViewById(R.id.startTime);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn = 1;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button endBtn = (Button) findViewById(R.id.endTime);
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn = 2;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextInputEditText inputTask = (TextInputEditText) findViewById(R.id.inputTask);
                task = inputTask.getText().toString();
                list.add(new Task(0, shour, smin, ehour, emin, task));
                Log.d("task", task);
                saveData();
                mAdapter.notifyDataSetChanged();
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

    private void saveData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(list);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
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