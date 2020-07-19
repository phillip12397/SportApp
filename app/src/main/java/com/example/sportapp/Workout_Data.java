package com.example.sportapp;

public class Workout_Data {

    private String dauer;
    private String workout;

    public Workout_Data(String dauer, String workout){
        this.dauer = dauer;
        this.workout = workout;
    }

    public String getDauer(){
        return dauer;
    }

    public String getWorkout(){
        return workout;
    }
}
