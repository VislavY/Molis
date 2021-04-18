package ru.vyapps.molis.screens.projects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import java.util.List;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Project;
import ru.vyapps.molis.screens.projects.adapters.ProjectsAdapter;
import ru.vyapps.molis.screens.projects.fragments.projectcreation.ProjectCreationBottomSheetFragment;
import ru.vyapps.molis.screens.projectPage.ProjectPageActivity;

public class ProjectsActivity extends AppCompatActivity {

    private ProjectsViewModel viewModel;

    private ProjectsAdapter projectsAdapter;

    private RecyclerView recyclerViewProjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewProjects = findViewById(R.id.recyclerViewProjects);

        viewModel = new ProjectsViewModel(getApplication());
        viewModel.getProjects().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                projectsAdapter = new ProjectsAdapter(projects);
                recyclerViewProjects.setAdapter(projectsAdapter);
                recyclerViewProjects.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

                projectsAdapter.setOnProjectClickListener(new ProjectsAdapter.OnProjectClickListener() {
                    @Override
                    public void onClick(Project project) {
                        Intent tasksScreenIntent = new Intent(getApplicationContext(), ProjectPageActivity.class);
                        tasksScreenIntent.putExtra("projectName", project.getName());
                        startActivity(tasksScreenIntent);
                    }
                });
            }
        });
    }

    public void onFABClick(View view) {
        ProjectCreationBottomSheetFragment projectCreationBottomSheet = new ProjectCreationBottomSheetFragment();
        projectCreationBottomSheet.show(getSupportFragmentManager(), ProjectCreationBottomSheetFragment.TAG);
    }
}