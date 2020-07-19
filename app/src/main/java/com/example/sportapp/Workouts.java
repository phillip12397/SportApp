package com.example.sportapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

public class Workouts extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Workouts_Adapter adapter;
    private List<Workout_Data> listWorkout;
    private ImageView imageStarBorder;
    private ImageView imageStar;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_workouts);
        listWorkout = new ArrayList<>();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.first_action_bar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_workout);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Workouts_Adapter(listWorkout);

        recyclerView.setAdapter(adapter);

        fillData();

        adapter.setOnItemClickListener(new Workouts_Adapter.OnWorkoutListener() {
            @Override
            public void onWorkoutClick(int position) {
                Intent intent = new Intent(Workouts.this, Weekday.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(Workouts.this);
            }
        });
    }

    private void fillData() {

        listWorkout.add(new Workout_Data("4 Wochen", "Lower Body"));
        listWorkout.add(new Workout_Data("2 Wochen", "Biceps and Back"));
        listWorkout.add(new Workout_Data("1 Wochen", "Legs"));
        listWorkout.add(new Workout_Data("4 Wochen", "Full Body "));
        adapter.notifyDataSetChanged();



    }
}
