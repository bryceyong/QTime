package com.example.qtime;

public class Task {
    private int day;
    private int startHour;
    private int startMin;
    private int endHour;
    private int endMin;
    private String task;

    public Task(int day, int startHour, int startMin, int endHour, int endMin, String task){
        this.day = day;
        this.startHour = startHour;
        this.startMin = startMin;
        this.endHour = endHour;
        this.endMin = endMin;
        this.task = task;
    }

    public int getDay(){
        return this.day;
    }

    public int getStartHour(){
        return this.startHour;
    }

    public int getStartMin(){
        return this.startMin;
    }

    public int getEndHour(){
        return this.endHour;
    }

    public int getEndMin(){
        return this.endMin;
    }

    public String getTask(){
        return this.task;
    }

    public String getTime(){

        String startH = Integer.toString(this.startHour);
        if(startH.length() != 2){
            startH = 0 + startH;
        }

        String startM = Integer.toString(this.startMin);
        if(startM.length() != 2){
            startM = 0 + startM;
        }

        String endH = Integer.toString(this.endHour);
        if(endH.length() != 2){
            endH = 0 + endH;
        }

        String endM = Integer.toString(this.endMin);
        if(endM.length() != 2){
            endM = 0 + endM;
        }
        return (startH + ":" + startM + " - " + endH + ":" + endM);
    }

}
