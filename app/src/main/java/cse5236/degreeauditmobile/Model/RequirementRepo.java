package cse5236.degreeauditmobile.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RequirementRepo {

    private final ProgressRequirementsDao mRequirementDao;
    private final LiveData<List<ProgressRequirements>> mAllRequirements;

    public RequirementRepo(Application application) {
        AppDatabase db = DatabaseSingleton.getDatabaseInstance("App_Database", application);
        mRequirementDao = db.progressRequirementsDao();
        mAllRequirements = mRequirementDao.getAllLive();
    }

    public boolean contains(String req) {
        return mRequirementDao.hasEntry(req);
    }

    public LiveData<List<ProgressRequirements>> getAllRequirements() {
        return mAllRequirements;
    }


    public void insert(ProgressRequirements u) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                mRequirementDao.insert(u));
    }

    public void update(ProgressRequirements u) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                mRequirementDao.update(u));
    }

    public void delete(ProgressRequirements u) {
        AppDatabase.databaseWriteExecutor.submit(() ->
                mRequirementDao.delete(u));
    }

}
