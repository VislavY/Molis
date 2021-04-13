package ru.vyapps.molis.models.database.projects;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ru.vyapps.molis.models.pojo.Project;

@Database(entities = Project.class, version = 1, exportSchema = false)
public abstract class ProjectsDatabase extends RoomDatabase {

    private static ProjectsDatabase instance;

    private static final String NAME = "projects.db";
    private static final Object LOCK = new Object();

    public static ProjectsDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (instance == null) {
                instance = Room.databaseBuilder(context, ProjectsDatabase.class, NAME).build();
            }
        }

        return instance;
    }

    public abstract ProjectsDao getDao();
}
