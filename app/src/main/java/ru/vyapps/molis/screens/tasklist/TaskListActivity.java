package ru.vyapps.molis.screens.tasklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Task;
import ru.vyapps.molis.screens.tasklist.adapters.task.TaskTouchHelperCallback;
import ru.vyapps.molis.screens.tasklist.adapters.task.TaskAdapter;
import ru.vyapps.molis.screens.tasklist.fragments.TaskCreationSheetFragment;

public class TaskListActivity extends AppCompatActivity  {

    private RecyclerView recyclerViewTasks;

    private TaskListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        viewModel = new TaskListViewModel(getApplication());
        viewModel.getTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                TaskAdapter taskAdapter = new TaskAdapter(getApplicationContext(), tasks);
                recyclerViewTasks.setAdapter(taskAdapter);
                recyclerViewTasks.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                TaskTouchHelperCallback callback = new TaskTouchHelperCallback(taskAdapter);
                ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
                itemTouchHelper.attachToRecyclerView(recyclerViewTasks);
            }
        });


    }

    public void onFABClick(View view) {
        TaskCreationSheetFragment taskCreationSheetFragment = new TaskCreationSheetFragment();
        taskCreationSheetFragment.show(getSupportFragmentManager(), "TaskCreationSheet");
    }
}