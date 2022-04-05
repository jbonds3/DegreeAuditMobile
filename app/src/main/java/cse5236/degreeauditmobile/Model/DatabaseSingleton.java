package cse5236.degreeauditmobile.Model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

public class DatabaseSingleton {
    static AppDatabase database;

    public static AppDatabase getDatabaseInstance(String name, Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context,
                    AppDatabase.class, name).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return database;
    }
}
