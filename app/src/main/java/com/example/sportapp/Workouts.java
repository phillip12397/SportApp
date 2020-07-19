package com.example.sportapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

import data.Excercise;
import data.Workout;
import data.WorkoutCollection;

public class Workouts extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Workouts_Adapter adapter;
    private List<Workout_Data> listWorkout;

    public static WorkoutCollection strengthBuilding = null;

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
        loadData();
        adapter.setOnItemClickListener(new Workouts_Adapter.OnWorkoutListener() {
            @Override
            public void onWorkoutClick(int position) {
                Intent intent = new Intent(Workouts.this, Weekday.class);
                intent.putExtra("Id", listWorkout.get(position).getId());
                startActivity(intent);
                Animatoo.animateSlideLeft(Workouts.this);
            }
        });
    }

    private void loadData() {

        listWorkout.add(new Workout_Data("0","1 Woche", "Strength Building"));
        listWorkout.add(new Workout_Data("1","4 Wochen", "Lower Body"));
        listWorkout.add(new Workout_Data("2","2 Wochen", "Biceps and Back"));
        listWorkout.add(new Workout_Data("3","1 Wochen", "Legs"));
        listWorkout.add(new Workout_Data("4","4 Wochen", "Full Body "));
        adapter.notifyDataSetChanged();

    }

    private void fillData() {

        Excercise pistolSquadLeft = new Excercise("10 Pistolsquads left side", 40, "android.resource://" + getPackageName() + "/" + R.raw.leftpistolsquad);
        Excercise pistolSquadRight = new Excercise("10 Pistolsquads right side", 40, "android.resource://" + getPackageName() + "/" + R.raw.rightpistolsquad);
        Excercise squad = new Excercise("25 Squads", 45, "android.resource://" + getPackageName() + "/" + R.raw.squad);
        Excercise pushUp = new Excercise("15 Pushups", 30, "android.resource://" + getPackageName() + "/" + R.raw.pushup);
        Excercise kneePushUp = new Excercise("30 Kneepushups", 60, "android.resource://" + getPackageName() + "/" + R.raw.kneepushup);
        Excercise bycicle = new Excercise("Bycicle", 45, "android.resource://" + getPackageName() + "/" + R.raw.bycicle);
        Excercise rep = new Excercise("15 Reps", 35, "android.resource://" + getPackageName() + "/" + R.raw.rep);
        Excercise jumpingJack = new Excercise("Jumping Jack", 60, "android.resource://" + getPackageName() + "/" + R.raw.jumpingjack);
        Excercise highSitUp = new Excercise("15 High SitUps", 45, "android.resource://" + getPackageName() + "/" + R.raw.highsitup);
        Excercise highPushUp = new Excercise("10 High Pushups", 45, "android.resource://" + getPackageName() + "/" + R.raw.highpushup);
        Excercise plank = new Excercise("Plank", 45, "android.resource://" + getPackageName() + "/" + R.raw.plank);
        Excercise buttkicks = new Excercise("Buttkicks", 35, "android.resource://" + getPackageName() + "/" + R.raw.buttkicks);
        Excercise topPushUp = new Excercise("10 Top Pushups", 30, "android.resource://" + getPackageName() + "/" + R.raw.toppushup);
        Excercise karateKid = new Excercise("Karate Kid", 40, "android.resource://" + getPackageName() + "/" + R.raw.karatekid);
        Excercise weirdoPushUp = new Excercise("Weirdo Pushup", 40, "android.resource://" + getPackageName() + "/" + R.raw.weirdopushup);
        Excercise plankKick = new Excercise("Plankkicks", 45, "android.resource://" + getPackageName() + "/" + R.raw.plankkick);

        Workout monday = new Workout("Upper Body", "Monday", pushUp, rep, highPushUp, karateKid, weirdoPushUp, kneePushUp, plank);
        Workout tuesday = new Workout("Abs", "Tuesday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout wednesday = new Workout("Whole Body", "Wednesday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout thursday = new Workout("Legs", "Thursday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout friday = new Workout("Upper Body", "Friday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout saturday = new Workout("Abs", "Saturday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout sunday = new Workout("Whole Body", "Sunday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);

        strengthBuilding = new WorkoutCollection("Strength Building", monday, tuesday, wednesday, thursday, friday, saturday, sunday);

    }

    /*
    private void readData() {

        try{
            InputStream is = getAssets().open("repository_workouts.json");
            int size = is.available();
            byte[] buffer = new byte[size];

            is.read();
            is.close();

            json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                Log.i("SportApp", obj.getString("UebungsName"));
                Excercise excercise = new Excercise(obj.getString("UebungsName"), obj.getInt("Sekunden"),obj.getString("VideoPath"));
                Workout workout = new Workout(obj.getString("MuskelGruppe"),obj.getString("Tag"), excercise);
                workoutCollection = new WorkoutCollection(obj.getString("Workout"),workout);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        if(workoutCollection != null) {
            for (int i = 0; i < workoutCollection.getAllWorkouts().size(); i++) {
                Log.i("SportApp", workoutCollection.getWorkout(i).getMuscles());
            }
        }
    }
*/
}
