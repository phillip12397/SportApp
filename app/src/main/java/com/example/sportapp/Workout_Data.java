package com.example.sportapp;

public class Workout_Data {

    private String dauer;
    private String workout;
    private String id;

    public Workout_Data(String id, String dauer, String workout){
        this.id = id;
        this.dauer = dauer;
        this.workout = workout;
    }

    public String getId(){
        return id;
    }

    public String getDauer(){
        return dauer;
    }

    public String getWorkout(){
        return workout;
    }
}
