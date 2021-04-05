package ru.vyapps.molis.screens.tasklist;

import java.util.ArrayList;

import ru.vyapps.molis.screens.tasklist.adapters.TasksAdapter;
import ru.vyapps.molis.models.pojo.Task;

public class TaskListPresenter implements TaskListContract.Presenter {

    private TaskListContract.View view;

    private ArrayList<Task> tasks;

    public TaskListPresenter(TaskListContract.View view) {
        this.view = view;
    }

    @Override
    public void loadTasks() {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        view.showTasks(tasks);
    }

    @Override
    public void createTask(String name) {
        Task task = new Task(name);
        tasks.add(task);

        view.showTask(tasks.size());
    }

    @Override
    public void taskNameChanged(String taskName) {
        if (!taskName.isEmpty()) {
            view.enableCreateTaskButton();
        } else {
            view.disableCreateTaskButton();
        }
    }
}