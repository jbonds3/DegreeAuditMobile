package cse5236.degreeauditmobile.Model;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

public class ClassRepo {

    private final ClassDao mClassDao;
    private final LiveData<List<Class>> mAllClass;

    public ClassRepo(Application application) {
        AppDatabase db = DatabaseSingleton.getDatabaseInstance("App_Database", application);
        mClassDao = db.classDao();
        mAllClass = mClassDao.getAll();
    }

    public LiveData<List<Class>> getAllClass() {
        return mAllClass;
    }

    public LiveData<Class> getClassByTitle(Class c) {
        return mClassDao.findByCourseID(c.title());
    }

    public void insert(Class c) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                mClassDao.insert(c));
    }

    public void delete(Class c) {
        AppDatabase.databaseWriteExecutor.submit(() ->
                mClassDao.delete(c));
    }

    private static class deleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void> {
        private ClassDao mAsyncTaskDao;

        deleteAllWordsAsyncTask(ClassDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }

    public void deleteAll()  {
        new deleteAllWordsAsyncTask(mClassDao).execute();
    }
}
