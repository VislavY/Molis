package ru.vyapps.molis.screens.projects.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Project;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    private ArrayList<Project> projects;

    public ProjectsAdapter(ArrayList<Project> projects) {
        this.projects = projects;
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent);
        return new ProjectsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        holder.checkedTextViewProjectsName.setText(projects.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    static class ProjectsViewHolder extends RecyclerView.ViewHolder {

        private CheckedTextView checkedTextViewProjectsName;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);

            checkedTextViewProjectsName = itemView.findViewById(R.id.checkedTextViewProjectName);
        }
    }
}
