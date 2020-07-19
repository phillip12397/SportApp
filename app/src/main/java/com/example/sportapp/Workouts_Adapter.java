package com.example.sportapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Workouts_Adapter extends RecyclerView.Adapter<Workouts_Adapter.WorkoutHolder> {

    private List<Workout_Data> mData;
    private Workouts_Adapter.OnWorkoutListener listener;

    public Workouts_Adapter(List<Workout_Data> mData){
        this.mData = mData;
    }

    @NonNull
    @Override
    public WorkoutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_item, parent, false);
        return new WorkoutHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder holder, int position) {
        Workout_Data currentWorkout = mData.get(position);
        holder.workout.setText(currentWorkout.getWorkout());
        holder.dauer.setText(currentWorkout.getDauer());
    }

    public interface OnWorkoutListener {
        void onWorkoutClick(int position);
    }

    public void setOnItemClickListener(OnWorkoutListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class WorkoutHolder extends RecyclerView.ViewHolder {

        private TextView dauer;
        private TextView workout;
        private ImageView starBorder;
        private ImageView star;

        public WorkoutHolder(@NonNull View itemView) {
            super(itemView);


            starBorder = (ImageView) itemView.findViewById(R.id.star_border);
            star = (ImageView) itemView.findViewById(R.id.star);
            dauer = (TextView) itemView.findViewById(R.id.dauer);
            workout = (TextView) itemView.findViewById(R.id.workout);

            starBorder.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    starBorder.setVisibility(View.GONE);
                    star.setVisibility(View.VISIBLE);
                }
            });

            star.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    starBorder.setVisibility(View.VISIBLE);
                    star.setVisibility(View.GONE);
                }
            });

            dauer = (TextView) itemView.findViewById(R.id.dauer);
            workout = (TextView) itemView.findViewById(R.id.workout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Workouts_Adapter.this.listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            Workouts_Adapter.this.listener.onWorkoutClick(position);
                        }
                    }
                }
            });
        }
    }
}
