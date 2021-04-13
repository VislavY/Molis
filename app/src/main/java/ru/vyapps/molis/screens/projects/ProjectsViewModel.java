package ru.vyapps.molis.screens.projects;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import ru.vyapps.molis.models.database.projects.ProjectsDatabase;
import ru.vyapps.molis.models.pojo.Project;

public class ProjectsViewModel extends AndroidViewModel {

    private ProjectsDatabase projectsDatabase;
    private LiveData<List<Project>> projects;

    public ProjectsViewModel(@NonNull Application application) {
        super(application);

        projectsDatabase = ProjectsDatabase.getInstance(application);
        projects = projectsDatabase.getDao().getAllTasks();
    }

    public LiveData<List<Project>> getProjects() {
        return projects;
    }
}
