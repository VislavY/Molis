package ru.vyapps.molis.models.database.tasks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.vyapps.molis.models.pojo.Task;

@Dao
public interface TasksDao {

    @Query("SELECT * FROM tasks")
    LiveData<List<Task>> getAll();

    @Query("SELECT * FROM tasks WHERE rootProject IN (:projectName)")
    LiveData<List<Task>> getAllByProject(String projectName);

    @Insert
    void insert(Task task);

    @Delete
    void delete(Task task);

    @Query("DELETE FROM tasks")
    void deleteAll();
}
