package ru.vyapps.molis.screens.tasklist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.vyapps.molis.models.database.tasks.TasksDatabase;
import ru.vyapps.molis.models.pojo.Task;

public class TaskListViewModel extends AndroidViewModel {

    private static TasksDatabase database;
    private LiveData<List<Task>> tasks;

    public TaskListViewModel(@NonNull Application application) {
        super(application);

        database = TasksDatabase.getInstance(application);
        tasks = database.getDao().getAllTasks();
    }

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }
}
