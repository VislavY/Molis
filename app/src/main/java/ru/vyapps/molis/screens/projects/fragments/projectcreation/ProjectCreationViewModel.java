package ru.vyapps.molis.screens.projects.fragments.projectcreation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ProjectCreationViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isProjectCreationButtonEnable;

    public ProjectCreationViewModel(@NonNull Application application) {
        super(application);

        isProjectCreationButtonEnable = new MutableLiveData<>();
    }

    public void projectNameChanged(String projectName) {
        isProjectCreationButtonEnable.setValue(!projectName.isEmpty());
    }

    public LiveData<Boolean> isProjectCreationButtonEnable() {
        return isProjectCreationButtonEnable;
    }
}
