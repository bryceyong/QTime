package com.example.qtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener{

    private ArrayList<Task> list = new ArrayList<>();
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
        setContentView(R.layout.activity_add);

        Button startBtn = (Button) findViewById(R.id.startTime);
        startBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btn = 1;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button endBtn = (Button) findViewById(R.id.endTime);
        endBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                btn = 2;
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        Button confirm = (Button) findViewById(R.id.confirm);
        confirm.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextInputEditText inputTask = (TextInputEditText) findViewById(R.id.inputTask);
                task = inputTask.getText().toString();

                list.add(new Task(0, shour, smin, ehour, emin, task));

            }
        });

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
}