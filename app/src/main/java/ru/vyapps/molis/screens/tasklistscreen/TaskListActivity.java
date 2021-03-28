package ru.vyapps.molis.screens.tasklistscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.adapters.TasksAdapter;
import ru.vyapps.molis.models.pojo.Task;
import ru.vyapps.molis.screens.createtaskscreen.TaskCreationSheet;

public class TaskListActivity extends AppCompatActivity implements TaskListView {

    private RecyclerView recyclerViewTasks;

    private TaskCreationSheet taskCreationSheet;

    private TaskListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        presenter = new TaskListPresenter(this);
        presenter.loadTasksAdapter();
    }

    @Override
    public void showTasks(TasksAdapter adapter) {
        recyclerViewTasks.setAdapter(adapter);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void onClickFAB(View view) {
        taskCreationSheet = new TaskCreationSheet(presenter);
        taskCreationSheet.show(getSupportFragmentManager(), "TaskCreationSheet");
    }
}