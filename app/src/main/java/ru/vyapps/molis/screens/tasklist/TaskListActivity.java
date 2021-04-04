package ru.vyapps.molis.screens.tasklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Task;
import ru.vyapps.molis.screens.tasklist.adapters.TasksAdapter;
import ru.vyapps.molis.screens.tasklist.fragments.TaskCreationSheetFragment;

public class TaskListActivity extends AppCompatActivity implements TaskListContract.View {

    private RecyclerView recyclerViewTasks;

    private TaskListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        presenter = new TaskListPresenter(this);
        presenter.loadTasks();
    }

    @Override
    public void showTasks(ArrayList<Task> tasks) {
        TasksAdapter adapter = new TasksAdapter(tasks);
        recyclerViewTasks.setAdapter(adapter);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void showTask(int taskIndex) {
        TasksAdapter adapter = (TasksAdapter) recyclerViewTasks.getAdapter();
        if (adapter != null) {
            adapter.notifyItemChanged(taskIndex);
        }
    }

    public void onClickFAB(View view) {
        TaskCreationSheetFragment taskCreationSheetFragment = new TaskCreationSheetFragment(presenter);
        taskCreationSheetFragment.show(getSupportFragmentManager(), "TaskCreationSheet");
    }
}