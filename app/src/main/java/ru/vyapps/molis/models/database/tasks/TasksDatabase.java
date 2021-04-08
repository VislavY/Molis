package ru.vyapps.molis.models.database.tasks;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.vyapps.molis.models.pojo.Task;

@Database(entities = Task.class, version = 1, exportSchema = false)
public abstract class TasksDatabase extends RoomDatabase {

    private static TasksDatabase instance;

    private final static String NAME = "tasks.db";
    private final static Object LOCK = new Object();

    public static TasksDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, TasksDatabase.class, NAME).build();
            }
        }

        return instance;
    }

    public abstract TasksDao getDao();
}
