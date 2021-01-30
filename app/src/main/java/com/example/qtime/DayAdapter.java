package com.example.qtime;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DayAdapter extends RecyclerView.Adapter<DayAdapter.ExampleViewHolder>{
    private ArrayList<Task> mList;

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView time;
        public TextView task;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time);
            task = itemView.findViewById(R.id.task);
        }
    }

    public DayAdapter(ArrayList<Task> list){
        mList = list;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.task, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {
        Task currentTask = mList.get(position);

        holder.time.setText(currentTask.getTime());
        holder.task.setText(currentTask.getTask());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
