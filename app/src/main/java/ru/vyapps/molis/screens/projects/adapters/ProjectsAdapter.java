package ru.vyapps.molis.screens.projects.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.vyapps.molis.R;
import ru.vyapps.molis.models.pojo.Project;

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectsAdapter.ProjectsViewHolder> {

    private OnProjectClickListener onProjectClickListener;
    private List<Project> projects;

    public ProjectsAdapter(List<Project> projects) {
        this.projects = projects;
    }

    @NonNull
    @Override
    public ProjectsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.project_item, parent, false);
        return new ProjectsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectsViewHolder holder, int position) {
        Project project = projects.get(position);
        holder.textViewProjectsName.setText(project.getName());
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setOnProjectClickListener(OnProjectClickListener l) {
        this.onProjectClickListener = l;
    }

    private interface OnProjectClickListener {
        void onClick();
    }

    class ProjectsViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewProjectsName;

        public ProjectsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewProjectsName = itemView.findViewById(R.id.textViewProjectName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onProjectClickListener != null) {
                        onProjectClickListener.onClick();
                    }
                }
            });
        }
    }
}
