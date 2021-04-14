package ru.vyapps.molis.screens.projectPage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.vyapps.molis.models.database.tasks.TasksDatabase;
import ru.vyapps.molis.models.pojo.Task;

public class ProjectPageViewModel extends AndroidViewModel {

    private static TasksDatabase database;

    public ProjectPageViewModel(@NonNull Application application) {
        super(application);

        database = TasksDatabase.getInstance(application);
    }

    public LiveData<List<Task>> getTasks(String projectName) {
        return database.getDao().getAllByProject(projectName);
    }
}
