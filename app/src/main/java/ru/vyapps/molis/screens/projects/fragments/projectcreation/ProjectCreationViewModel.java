package ru.vyapps.molis.screens.projects.fragments.projectcreation;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import ru.vyapps.molis.models.database.projects.ProjectsDatabase;
import ru.vyapps.molis.models.pojo.Project;

public class ProjectCreationViewModel extends AndroidViewModel {

    private static ProjectsDatabase projectsDatabase;
    private MutableLiveData<Boolean> isProjectCreationButtonEnable;

    public ProjectCreationViewModel(@NonNull Application application) {
        super(application);

        projectsDatabase = ProjectsDatabase.getInstance(application);
        isProjectCreationButtonEnable = new MutableLiveData<>();
    }

    public void projectNameChanged(@NonNull String projectName) {
        isProjectCreationButtonEnable.setValue(!projectName.isEmpty());
    }

    public void createProject(@NonNull String name) {
        Project project = new Project(name);
        new InsertProjectTask().execute(project);
    }

    public LiveData<Boolean> isProjectCreationButtonEnable() {
        return isProjectCreationButtonEnable;
    }

    private static class InsertProjectTask extends AsyncTask<Project, Void, Void> {
        @Override
        protected Void doInBackground(Project... projects) {
            if (projects != null && projects.length > 0) {
                projectsDatabase.getDao().insertProject(projects[0]);
            }

            return null;
        }
    }
}
