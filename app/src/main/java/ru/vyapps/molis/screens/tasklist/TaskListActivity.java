package ru.vyapps.molis.screens.tasklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Task;
import ru.vyapps.molis.screens.tasklist.adapters.task.TaskTouchHelperCallback;
import ru.vyapps.molis.screens.tasklist.adapters.task.TaskAdapter;
import ru.vyapps.molis.screens.tasklist.fragments.TaskCreationSheetFragment;

public class TaskListActivity extends AppCompatActivity implements TaskListContract.View, TextWatcher {

    private TaskCreationSheetFragment taskCreationSheet;

    private RecyclerView recyclerViewTasks;

    private TaskListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        taskCreationSheet = new TaskCreationSheetFragment(this);

        recyclerViewTasks = findViewById(R.id.recyclerViewTasks);

        presenter = new TaskListPresenter(this);
        presenter.loadTasks();
    }

    @Override
    public void showTasks(ArrayList<Task> tasks) {
        TaskAdapter adapter = new TaskAdapter(tasks);
        recyclerViewTasks.setAdapter(adapter);
        recyclerViewTasks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        TaskTouchHelperCallback taskTouchHelperCallback = new TaskTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(taskTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(recyclerViewTasks);
    }

    @Override
    public void showTask(int taskIndex) {
        TaskAdapter adapter = (TaskAdapter) recyclerViewTasks.getAdapter();
        if (adapter != null) {
            adapter.notifyItemChanged(taskIndex);
        }
    }

    @Override
    public void enableCreateTaskButton() {
        Button buttonCreateTask = taskCreationSheet.getButtonCreateTask();
        buttonCreateTask.setEnabled(true);
    }

    @Override
    public void disableCreateTaskButton() {
        Button buttonCreateTask = taskCreationSheet.getButtonCreateTask();
        buttonCreateTask.setEnabled(false);
    }

    public void onClickFAB(View view) {
        taskCreationSheet.show(getSupportFragmentManager(), "TaskCreationSheet");
    }

    public void onClickCreateTaskButton(View view) {
        EditText editTextTaskName = taskCreationSheet.getEditTextTaskName();
        String taskName = editTextTaskName.getText().toString().trim();
        presenter.createTask(taskName);

        taskCreationSheet.dismiss();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        presenter.taskNameChanged(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}