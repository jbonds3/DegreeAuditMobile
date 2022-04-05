package cse5236.degreeauditmobile.Model;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

public class SemesterRepo {

    private final SemesterDao mSemesterDao;
    private final LiveData<List<Semester>> mAllSemester;

    public SemesterRepo(Application application) {
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "database-name").allowMainThreadQueries().fallbackToDestructiveMigration().build();
        mSemesterDao = db.semesterDao();
        mAllSemester = mSemesterDao.getAll();
    }

    public LiveData<List<Semester>> getAllSemester() { return mAllSemester;}

    public LiveData<Semester> getSemByID(Semester semester) {
        LiveData<Semester> retSem = mSemesterDao.findBySemesterID(semester.getSemesterID());
        return retSem;
    }

    public boolean hasSemByID(Semester semester) {
        return mSemesterDao.hasEntry(semester.getSemesterID());
    }

    public LiveData<List<Class>> getClassesBySemID(Semester semester) {
        return mSemesterDao.getClassesFromSemesterID(semester.getSemesterID());
    }

    public void insert(Semester semester) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                mSemesterDao.insert(semester));
    }

    public LiveData<Semester> getSemesterbySemID(String semesterID) {
        return mSemesterDao.findBySemesterID(semesterID);
    }
    public void delete(Semester semester) {
        AppDatabase.databaseWriteExecutor.submit(() ->
                mSemesterDao.delete(semester));
    }


    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private SemesterDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(SemesterDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mSemesterDao).execute();
    }

}
