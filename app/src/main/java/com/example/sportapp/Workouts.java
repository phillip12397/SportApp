package com.example.sportapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import data.Excercise;
import data.Workout;
import data.WorkoutCollection;

public class Workouts extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Workouts_Adapter adapter;
    private List<Workout_Data> listWorkout;

    private static String strengthB;/* = "Strength Building;false";*/
    private static String lowerBody;/* = "Lower Body;false";*/
    private static String bicepsAndBack;/* = "Biceps and Back;false";*/
    private static String legs;/* = "Legs;false";*/
    private static String fullBody; /* = "Full Body;false";*/

    private static Workouts instance;

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
        instance = this;
    }

    private void loadData() {
        listWorkout.add(new Workout_Data("0", "1 Woche", "Strength Building"));
        listWorkout.add(new Workout_Data("1", "4 Wochen", "Lower Body"));
        listWorkout.add(new Workout_Data("2", "2 Wochen", "Biceps and Back"));
        listWorkout.add(new Workout_Data("3", "1 Wochen", "Legs"));
        listWorkout.add(new Workout_Data("4", "4 Wochen", "Full Body "));
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

        Workout monday = new Workout("Upper Body", "Monday", pushUp, rep, highPushUp, topPushUp, karateKid, weirdoPushUp, kneePushUp);
        Workout tuesday = new Workout("Abs", "Tuesday", highSitUp, bycicle, plank, karateKid, plankKick);
        Workout wednesday = new Workout("Whole Body", "Wednesday", highSitUp, bycicle, plank, karateKid, jumpingJack, buttkicks);
        Workout thursday = new Workout("Legs", "Thursday", jumpingJack, buttkicks, squad, pistolSquadLeft, pistolSquadRight);
        Workout friday = new Workout("Upper Body", "Friday", pushUp, rep, highPushUp, topPushUp, karateKid, weirdoPushUp, kneePushUp);
        Workout saturday = new Workout("Abs", "Saturday", highSitUp, bycicle, plank, karateKid, plankKick);
        Workout sunday = new Workout("Whole Body", "Sunday", jumpingJack, buttkicks, pushUp, rep, highPushUp, topPushUp);

        strengthBuilding = new WorkoutCollection("Strength Building", monday, tuesday, wednesday, thursday, friday, saturday, sunday);
    }

    public static void save(String workout, String star) {
/*
       switch (workout){
           case "Strength Building": strengthB = workout +";"+ star;break;
           case "Lower Body" : lowerBody = workout +";"+ star;break;
           case "Biceps and Back" : bicepsAndBack = workout +";"+ star;break;
           case "Legs" : legs = workout +";"+ star;break;
           case "Full Body" : fullBody = workout +";"+ star;break;
           default:break;
       }
*/
        String txt = workout + ";" + star;
        HashMap<String, String> loadedWorkout = load();
        String oldContent = "";
        String oldString = "";
        if (loadedWorkout.containsKey(workout)) {
            if (star.equals("true")) oldString = workout + ";false";
            if (star.equals("false")) oldString = workout + ";true";
        }

        try {
            OutputStreamWriter osw = new OutputStreamWriter(instance.getApplicationContext().openFileOutput("workout.txt", Context.MODE_PRIVATE));
            if(!oldString.equals("")) {
                InputStreamReader isr = new InputStreamReader(instance.getApplication().openFileInput("workout.txt"));
                BufferedReader br = new BufferedReader(isr);
                String line;
                while ((line = br.readLine()) != null) {
                    oldContent = oldContent + line + System.lineSeparator();
                }

                String newContent = oldContent.replaceAll(oldString, workout + ";star");

                osw.write(newContent);
                osw.close();
            } else {
                osw.write(workout + ";" + star);
                osw.close();
            }
            Log.i("SportApp", txt);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, String> load() {
        HashMap<String, String> loadWorkout = new HashMap<>();

        try {
            InputStreamReader isr = new InputStreamReader(instance.getApplication().openFileInput("workout.txt"));
            BufferedReader br = new BufferedReader(isr);
            String text;

            while ((text = br.readLine()) != null) {
                Log.i("loadSportApp", text);
                String[] workout = text.split(";");
                loadWorkout.put(workout[0].trim(), workout[1].trim());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadWorkout;
    }
/*
    @Override
    protected void onDestroy() {
        super.onDestroy();

        String path = getApplicationContext().getFilesDir().getAbsolutePath();
        try {
            File file = new File(path +"/workout.txt");
            if(file.exists()) file.delete();
            OutputStreamWriter osw = new OutputStreamWriter(instance.getApplicationContext().openFileOutput("workout.txt", Context.MODE_PRIVATE));
            osw.write(strengthB);
            osw.write(lowerBody);
            osw.write(bicepsAndBack);
            osw.write(legs);
            osw.write(fullBody);
            osw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
*/
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
