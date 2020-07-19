package com.example.sportapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import data.Excercise;
import data.Workout;
import data.WorkoutCollection;

public class Weekday extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Weekday_Adapter adapter;
    private List<Weekday_Data> listWeekday;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_weekdays);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        listWeekday = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_weekday);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Weekday_Adapter(listWeekday);

        recyclerView.setAdapter(adapter);

        fillData();

        adapter.setOnItemClickListener(new Weekday_Adapter.OnWeekdayListener() {
            @Override
            public void onWeekdayClick(int position) {
                Intent intent = new Intent(Weekday.this, Choosed_Workout.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(Weekday.this);
            }
        });
    }

    private void fillData() {

        Excercise pistolSquadLeft = new Excercise("10 Pistolsquads left side", 40);
        Excercise pistolSquadRight = new Excercise("10 Pistolsquads right side", 40);
        Excercise squad = new Excercise("25 Squads", 45);
        Excercise pushUp = new Excercise("15 Pushups", 30);
        Excercise kneePushUp = new Excercise("30 Kneepushups", 60);
        Excercise bycicle = new Excercise("Bycicle", 45);
        Excercise rep = new Excercise("15 Reps", 35);
        Excercise jumpingJack = new Excercise("Jumping Jack", 60);
        Excercise highSitUp = new Excercise("15 High SitUps", 45);
        Excercise highPushUp = new Excercise("10 High Pushups", 45);
        Excercise plank = new Excercise("Plank", 45);
        Excercise buttkicks = new Excercise("Buttkicks", 35);
        Excercise topPushUp = new Excercise("10 Top Pushups", 30);
        Excercise karateKid = new Excercise("Karate Kid", 40);
        Excercise weirdoPushUp = new Excercise("Weirdo Pushup", 40);
        Excercise plankKick = new Excercise("Plankkicks", 45);

        Workout monday = new Workout("Upper Body", "Monday", pushUp, rep, highPushUp, karateKid, weirdoPushUp, kneePushUp, plank);
        Workout tuesday = new Workout("Abs", "Tuesday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout wednesday = new Workout("Whole Body", "Wednesday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout thursday = new Workout("Legs", "Thursday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout friday = new Workout("Upper Body", "Friday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout saturday = new Workout("Abs", "Saturday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout sunday = new Workout("Whole Body", "Sunday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);

        WorkoutCollection strengthBuilding = new WorkoutCollection("Strength Building", monday, tuesday, wednesday, thursday, friday, saturday, sunday);


        listWeekday.add(new Weekday_Data("Biceps and Back", "Monday"));
        listWeekday.add(new Weekday_Data("Triceps", "Tuesday"));
        listWeekday.add(new Weekday_Data("Rest", "Wednesday"));
        listWeekday.add(new Weekday_Data("Abs", "Thursday"));
        listWeekday.add(new Weekday_Data("Chest", "Friday"));
        listWeekday.add(new Weekday_Data("Rest", "Saturday"));
        listWeekday.add(new Weekday_Data("Rest", "Sunday"));
        adapter.notifyDataSetChanged();
    }
}
