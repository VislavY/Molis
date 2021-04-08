package ru.vyapps.molis.screens.tasklist.adapters.task;

import android.content.Context;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.database.tasks.TasksDatabase;
import ru.vyapps.molis.models.pojo.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TasksViewHolder> implements  TaskTouchHelperAdapter {

    private static TasksDatabase database;
    private List<Task> tasks;

    public TaskAdapter(Context context, List<Task> tasks) {
        this.tasks = tasks;

        database = TasksDatabase.getInstance(context);
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
        holder.checkedTextViewTaskName.setText(task.getName());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

//    @Override
//    public void onTaskMove(int fromPosition, int toPosition) {
//        if (fromPosition < toPosition) {
//            for (int i = fromPosition; i < toPosition; i++) {
//                Collections.swap(tasks, i, i + 1);
//            }
//        } else {
//            for (int i = fromPosition; i > toPosition; i--) {
//                Collections.swap(tasks, i, i - 1);
//            }
//        }
//
//        notifyItemMoved(fromPosition, toPosition);
//    }

    @Override
    public void onTaskDismiss(int position) {
        Task task = tasks.get(position);
        new DeleteTaskTask().execute(task);

        tasks.remove(position);
        notifyItemRemoved(position);
    }

    private static class DeleteTaskTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            if (tasks != null && tasks.length > 0) {
                database.getDao().deleteTask(tasks[0]);
            }

            return null;
        }
    }

    static class TasksViewHolder extends RecyclerView.ViewHolder  {
        private CheckedTextView checkedTextViewTaskName;

        public TasksViewHolder(@NonNull View itemView) {
            super(itemView);

            checkedTextViewTaskName = itemView.findViewById(R.id.textViewTitle);

            checkedTextViewTaskName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    checkedTextViewTaskName.setChecked(!checkedTextViewTaskName.isChecked());

                    if (checkedTextViewTaskName.isChecked()) {
                        checkedTextViewTaskName.setPaintFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        checkedTextViewTaskName.setPaintFlags(0);
                    }
                }
            });
        }
    }
}
