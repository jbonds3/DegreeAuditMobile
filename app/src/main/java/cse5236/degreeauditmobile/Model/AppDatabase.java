package cse5236.degreeauditmobile.Model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Class.class, Semester.class, ProgressRequirements.class, RequirementClass.class}, version = 8, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    private static final int sNumberOfThreads = 2;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(sNumberOfThreads);
    public abstract ClassDao classDao();

    public abstract SemesterDao semesterDao();

    public abstract ProgressRequirementsDao progressRequirementsDao();
    public abstract RequirementClassDao requirementClassDao();

}
