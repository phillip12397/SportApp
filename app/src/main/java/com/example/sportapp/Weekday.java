package com.example.sportapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import data.Workout;

public class Weekday extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Weekday_Adapter adapter;
    private List<Weekday_Data> listWeekday;
    private ImageView arrowBack;

    private String idWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_weekdays);


        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            idWorkout = extra.getString("Id");
        }

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        listWeekday = new ArrayList<>();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_weekday);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new Weekday_Adapter(listWeekday);

        recyclerView.setAdapter(adapter);

        loadData();

        adapter.setOnItemClickListener(new Weekday_Adapter.OnWeekdayListener() {
            @Override
            public void onWeekdayClick(int position) {
                Intent intent = new Intent(Weekday.this, Choosed_Workout.class);
                intent.putExtra("Id", listWeekday.get(position).getId());
                intent.putExtra("IdWorkout", idWorkout);
                startActivity(intent);
                Animatoo.animateSlideLeft(Weekday.this);
            }
        });

        arrowBack = (ImageView) findViewById(R.id.arrow_back);

        arrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Weekday.this, Workouts.class);
                startActivity(intent);
                Animatoo.animateSlideRight(Weekday.this);
            }
        });
    }

    private void loadData() {

        switch (idWorkout){
            case "0" :  List<Workout> workout = Workouts.strengthBuilding.getAllWorkouts();
                listWeekday.add(new Weekday_Data("0", workout.get(0).getMuscles(), workout.get(0).getDay()));
                listWeekday.add(new Weekday_Data("1", workout.get(1).getMuscles(), workout.get(1).getDay()));
                listWeekday.add(new Weekday_Data("2", workout.get(2).getMuscles(), workout.get(2).getDay()));
                listWeekday.add(new Weekday_Data("3", workout.get(3).getMuscles(), workout.get(3).getDay()));
                listWeekday.add(new Weekday_Data("4", workout.get(4).getMuscles(), workout.get(4).getDay()));
                listWeekday.add(new Weekday_Data("5", workout.get(5).getMuscles(), workout.get(5).getDay()));
                listWeekday.add(new Weekday_Data("6", workout.get(6).getMuscles(), workout.get(6).getDay()));
                adapter.notifyDataSetChanged();
                break;
            case "1" : break;
            case "2" : break;
            case "3" : break;
            case "4" : break;
            default: break;
        }
    }
}
