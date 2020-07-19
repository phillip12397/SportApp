package com.example.sportapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class Weekday_Adapter extends RecyclerView.Adapter<Weekday_Adapter.WeekdayHolder> {

    private List<Weekday_Data> mList;
    private Weekday_Adapter.OnWeekdayListener listener;

    public Weekday_Adapter(List<Weekday_Data> mList){
        this.mList = mList;
    }

    @NonNull
    @Override
    public WeekdayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weekday_item, parent, false);
        return new Weekday_Adapter.WeekdayHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekdayHolder holder, int position) {
        Weekday_Data currentWeekday = mList.get(position);
        holder.muscle.setText(currentWeekday.getMuscle());
        holder.weekday.setText(currentWeekday.getWeekday());
    }

    @Override
    public int getItemCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public interface OnWeekdayListener {
        void onWeekdayClick(int position);
    }

    public void setOnItemClickListener(Weekday_Adapter.OnWeekdayListener listener){
        this.listener = listener;
    }

    public class WeekdayHolder extends RecyclerView.ViewHolder {

        private TextView muscle;
        private TextView weekday;

        public WeekdayHolder(@NonNull View itemView) {
            super(itemView);

            muscle = (TextView) itemView.findViewById(R.id.muscle);
            weekday = (TextView) itemView.findViewById(R.id.weekday);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(Weekday_Adapter.this.listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            Weekday_Adapter.this.listener.onWeekdayClick(position);
                        }
                    }
                }
            });
        }
    }
}
