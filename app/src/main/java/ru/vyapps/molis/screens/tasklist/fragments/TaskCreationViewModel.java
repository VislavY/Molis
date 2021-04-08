package ru.vyapps.molis.screens.tasklist.fragments;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.vyapps.molis.models.database.tasks.TasksDatabase;
import ru.vyapps.molis.models.pojo.Task;

public class TaskCreationViewModel extends AndroidViewModel {

    private static TasksDatabase database;

    private MutableLiveData<Boolean> createTaskButtonEnabled;

    public TaskCreationViewModel(@NonNull Application application) {
        super(application);

        database = TasksDatabase.getInstance(application);
        createTaskButtonEnabled = new MutableLiveData<>();
    }

    public void taskNameChanged(String taskName) {
        createTaskButtonEnabled.setValue(!taskName.isEmpty());
    }

    public void createTask(String name) {
        Task task = new Task(name);
        new InsertTaskTask().execute(task);
    }

    public LiveData<Boolean> getCreateTaskButtonEnabled() {
        return createTaskButtonEnabled;
    }

    private static class InsertTaskTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            if (tasks != null && tasks.length > 0) {
                database.getDao().insertTask(tasks[0]);
            }

            return null;
        }
    }

    public void a () {
        System.out.println(database.getDao().getAllTasks());
    }
}
