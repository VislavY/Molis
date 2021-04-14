package ru.vyapps.molis.models.pojo;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tasks")
public class Task {

    @PrimaryKey(autoGenerate = true)
    private int uid;
    private String rootProject;
    private String name;

    public Task(int uid, String rootProject, String name) {
        this.uid = uid;
        this.rootProject = rootProject;
        this.name = name;
    }

    @Ignore
    public Task(String rootProject, String name) {
        this.rootProject = rootProject;
        this.name = name;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setRootProject(String projectName) {
        this.rootProject = projectName;
    }

    public String getRootProject() {
        return rootProject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
