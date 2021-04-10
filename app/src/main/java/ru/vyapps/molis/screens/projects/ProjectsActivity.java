package ru.vyapps.molis.screens.projects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import ru.vyapps.molis.R;
import ru.vyapps.molis.screens.projects.adapters.ProjectsAdapter;
import ru.vyapps.molis.screens.projects.fragments.projectcreation.ProjectCreationBottomSheetFragment;

public class ProjectsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProjects;

    private ProjectsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        recyclerViewProjects = findViewById(R.id.recyclerViewProjects);
        ProjectsAdapter adapter = new ProjectsAdapter(null);
        recyclerViewProjects.setAdapter(adapter);

        viewModel = new ProjectsViewModel(getApplication());
    }

    public void onFABClick(View view) {
        ProjectCreationBottomSheetFragment projectCreationBottomSheet = new ProjectCreationBottomSheetFragment();
        projectCreationBottomSheet.show(getSupportFragmentManager(), ProjectCreationBottomSheetFragment.TAG);
    }
}