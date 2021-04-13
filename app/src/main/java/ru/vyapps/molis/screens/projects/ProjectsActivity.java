package ru.vyapps.molis.screens.projects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Project;
import ru.vyapps.molis.screens.projects.adapters.ProjectsAdapter;
import ru.vyapps.molis.screens.projects.fragments.projectcreation.ProjectCreationBottomSheetFragment;

public class ProjectsActivity extends AppCompatActivity {

    private ProjectsViewModel viewModel;

    private RecyclerView recyclerViewProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        recyclerViewProjects = findViewById(R.id.recyclerViewProjects);

        viewModel = new ProjectsViewModel(getApplication());
        System.out.println(viewModel.getProjects().getValue());
        viewModel.getProjects().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                ProjectsAdapter adapter = new ProjectsAdapter(projects);
                recyclerViewProjects.setAdapter(adapter);
                recyclerViewProjects.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
            }
        });
    }

    public void onFABClick(View view) {
        ProjectCreationBottomSheetFragment projectCreationBottomSheet = new ProjectCreationBottomSheetFragment();
        projectCreationBottomSheet.show(getSupportFragmentManager(), ProjectCreationBottomSheetFragment.TAG);
    }
}