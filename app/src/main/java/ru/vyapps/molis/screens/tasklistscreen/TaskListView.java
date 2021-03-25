package ru.vyapps.molis.screens.tasklistscreen;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.vyapps.molis.models.adapters.TasksAdapter;
import ru.vyapps.molis.models.pojo.Task;

public interface TaskListView {

    void showTasks(TasksAdapter adapter);
}
