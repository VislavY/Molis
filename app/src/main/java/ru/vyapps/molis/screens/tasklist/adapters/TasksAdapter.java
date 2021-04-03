package ru.vyapps.molis.screens.tasklist.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Task;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {

    private ArrayList<Task> tasks;

    public TasksAdapter(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TasksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TasksViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textViewTitle.setText(task.getTitle());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewTitle;
        private View buttonCompleteTask;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            buttonCompleteTask = itemView.findViewById(R.id.buttonCompleteTask);

            buttonCompleteTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int adapterPosition = getAdapterPosition();
                    tasks.remove(adapterPosition);
                    notifyItemRemoved(adapterPosition);
                }
            });
        }
    }
}
