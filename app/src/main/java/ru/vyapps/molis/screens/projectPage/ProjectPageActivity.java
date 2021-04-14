package ru.vyapps.molis.screens.projectPage;

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
import ru.vyapps.molis.screens.projectPage.adapters.task.TaskTouchHelperCallback;
import ru.vyapps.molis.screens.projectPage.adapters.task.TaskAdapter;
import ru.vyapps.molis.screens.projectPage.fragments.TaskCreationSheetFragment;

public class ProjectPageActivity extends AppCompatActivity  {

    private String projectName;

    private ProjectPageViewModel viewModel;

    private RecyclerView recyclerViewTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        projectName = getIntent().getStringExtra("projectName");
        getSupportActionBar().setTitle(projectName);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        viewModel = new ProjectPageViewModel(getApplication());
        viewModel.getTasks(projectName).observe(this, new Observer<List<Task>>() {
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
        TaskCreationSheetFragment taskCreationSheetFragment = new TaskCreationSheetFragment(projectName);
        taskCreationSheetFragment.show(getSupportFragmentManager(), "TaskCreationSheet");
    }
}