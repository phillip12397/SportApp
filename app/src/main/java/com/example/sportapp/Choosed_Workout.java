package com.example.sportapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.List;

import data.Workout;

public class Choosed_Workout extends AppCompatActivity {

    private  TextView anzahl;
    private TextView name;
    private VideoView videoView;
    private Button zurueck;
    private Button weiter;

    private boolean running;
    private long pauseOffset;
    private Chronometer chronometer;
    private int currentExerciseTime;

    private ImageView arrowBack;
    private List<Workout> workout;
    private int zaehler;

    private String idWeekday;
    private String idAsString;
    private int idAsInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosed_workout);

        Bundle extra = getIntent().getExtras();
        if (extra != null){
            idAsString = extra.getString("Id");
            idWeekday = extra.getString("IdWorkout");
        }

        idAsInt = Integer.parseInt(idAsString);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);

        arrowBack = (ImageView) findViewById(R.id.arrow_back);

        arrowBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Choosed_Workout.this, Weekday.class);
                intent.putExtra("Id", idWeekday);
                startActivity(intent);
                Animatoo.animateSlideRight(Choosed_Workout.this);
            }
        });

        anzahl = (TextView) findViewById(R.id.anzahl);
        name = (TextView) findViewById(R.id.uebungsName);
        videoView = (VideoView) findViewById(R.id.videoView);
        zurueck = (Button) findViewById(R.id.zurück);
        weiter = (Button) findViewById(R.id.weiter);

        workout = Workouts.strengthBuilding.getAllWorkouts();
        zaehler = 0;

        loadFirstExcercise();

        weiter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(zaehler < workout.get(idAsInt).getSize() - 1) forward();
            }
        });

        zurueck.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(zaehler >= 1) back();
            }
        });

        //----------------------------------------------------------------------------------------
        // Hier wird der Timerinitialisiert und startet und stopt per Buttondruck

        final ToggleButton stopWatch = findViewById(R.id.toggleButton);
        chronometer = findViewById(R.id.timer);

        stopWatch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    startChronometer();
                    stopWatch.setBackgroundColor(Color.parseColor("#873F3F"));
                } else {
                    pauseChronometer();
                    stopWatch.setBackgroundColor(Color.parseColor("#717171"));
                }
            }
        });

        currentExerciseTime = 20003;

        chronometer.setFormat("%s");
        chronometer.setBase(SystemClock.elapsedRealtime() + currentExerciseTime);
        chronometer.setCountDown(true);

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {

                if ((SystemClock.elapsedRealtime() - chronometer.getBase()) >= 10000) {
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    System.out.println("Time: " + SystemClock.elapsedRealtime());
                }
            }
        });
    }

    public void startChronometer() {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset + currentExerciseTime);
            chronometer.start();
            running = true;
        }
    }

    public void pauseChronometer() {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase() + currentExerciseTime;
            running = false;
        }
    }

    //----------------------------------------------------------------------------------------
    // Hier wird die erste Uebung geladen

    private void loadFirstExcercise() {
        name.setText(workout.get(idAsInt).getExcercise(0).getTitle());
        //chronometer.setCountDown(workout.get(0).getExcercise(0).getSeconds());
        onResume(workout.get(idAsInt).getExcercise(0).getVideoPath());
        anzahl.setText(zaehler + 1 + "/" + workout.get(idAsInt).getSize());
    }

    //----------------------------------------------------------------------------------------
    // Hier geht man entweder eine Übung weiter oder zurück

    private void forward() {
        zaehler++;
        name.setText(workout.get(idAsInt).getExcercise(zaehler).getTitle());
        onResume(workout.get(idAsInt).getExcercise(zaehler).getVideoPath());
        anzahl.setText(zaehler + 1 +"/" + workout.get(idAsInt).getSize());
    }

    private void back(){
        zaehler--;
        name.setText(workout.get(idAsInt).getExcercise(zaehler).getTitle());
        onResume(workout.get(idAsInt).getExcercise(zaehler).getVideoPath());
        anzahl.setText(zaehler + 1 +"/" + workout.get(idAsInt).getSize());
    }

    //----------------------------------------------------------------------------------------
    // Hier wird das passende Übungsvideo gemuted und in Dauerschleife gezeigt

    protected void onResume(String videoPath) {
        super.onResume();

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();

        videoView.setOnPreparedListener (new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mp.setVolume(0.0f, 0.0f);
            }
        });
    }
}
