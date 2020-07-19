package com.example.sportapp;

public class Weekday_Data {

    String muscle;
    String weekday;

    public Weekday_Data(String muscle, String weekday){
        this.muscle = muscle;
        this.weekday = weekday;
    }

    public String getMuscle(){
        return muscle;
    }

    public  String getWeekday(){
        return weekday;
    }
}
