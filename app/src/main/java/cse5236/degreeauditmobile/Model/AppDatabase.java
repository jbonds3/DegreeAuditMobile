package cse5236.degreeauditmobile.Model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Class.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract ClassDao classDao();
}
