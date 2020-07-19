package com.example.sportapp;

public class Weekday_Data {

    String muscle;
    String weekday;
    String id;

    public Weekday_Data(String id, String muscle, String weekday){
        this.id = id;
        this.muscle = muscle;
        this.weekday = weekday;
    }

    public String getId(){
        return id;
    }

    public String getMuscle(){
        return muscle;
    }

    public  String getWeekday(){
        return weekday;
    }
}
