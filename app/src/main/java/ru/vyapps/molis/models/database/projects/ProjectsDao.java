package ru.vyapps.molis.models.database.projects;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.vyapps.molis.models.pojo.Project;

@Dao
public interface ProjectsDao {

    @Query("SELECT * FROM projects")
    LiveData<List<Project>> getAllTasks();

    @Insert
    void insertProject(Project project);

    @Delete
    void deleteProject(Project project);
}
