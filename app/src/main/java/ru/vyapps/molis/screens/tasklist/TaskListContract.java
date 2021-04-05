package ru.vyapps.molis.screens.tasklist;

import java.util.ArrayList;

import ru.vyapps.molis.models.pojo.Task;

public interface TaskListContract {

    interface View {
        void showTasks(ArrayList<Task> tasks);
        void showTask(int index);
        void enableCreateTaskButton();
        void disableCreateTaskButton();
    }

    interface Presenter {
        void loadTasks();
        void createTask(String name);
        void taskNameChanged(String taskName);
    }
}