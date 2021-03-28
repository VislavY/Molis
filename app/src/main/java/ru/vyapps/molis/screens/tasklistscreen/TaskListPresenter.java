package ru.vyapps.molis.screens.tasklistscreen;

import java.util.ArrayList;

import ru.vyapps.molis.models.adapters.TasksAdapter;
import ru.vyapps.molis.models.pojo.Task;

public class TaskListPresenter {

    private TaskListView view;

    private ArrayList<Task> tasks;
    private TasksAdapter adapter;

    public TaskListPresenter(TaskListView view) {
        this.view = view;
    }

    public void loadTasksAdapter() {
        if (adapter == null) {
            tasks = new ArrayList<>();
            adapter = new TasksAdapter(tasks);
        }

        view.showTasks(adapter);
    }

    public void createTask(String taskName) {
        Task task = new Task(taskName);
        tasks.add(task);
        adapter.notifyDataSetChanged();
    }
}
