package ru.vyapps.molis.screens.tasklistscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.vyapps.molis.R;

public class TaskListActivity extends AppCompatActivity implements TaskListView {

    private TaskListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        presenter = new TaskListPresenter(this);
    }
}