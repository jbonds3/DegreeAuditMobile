package cse5236.degreeauditmobile.Model;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ReqToClassRepo {

    private final RequirementClassDao mRequirementClassDao;
    private final LiveData<List<RequirementClass>> mAllReqToClass;

    public ReqToClassRepo(Application application) {
        AppDatabase db = DatabaseSingleton.getDatabaseInstance("App_Database", application);
        mRequirementClassDao = db.requirementClassDao();
        mAllReqToClass = mRequirementClassDao.getAllLive();
    }

    public LiveData<List<RequirementClass>> getAllReqToClass() {
        return mAllReqToClass;
    }

    public LiveData<List<RequirementClass>> findByRequirement(String requirement) {
        return mRequirementClassDao.findByRequirementLive(requirement);
    }


    public void insert(RequirementClass u) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                mRequirementClassDao.insert(u));
    }

    public void update(RequirementClass u) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                mRequirementClassDao.update(u));
    }

    public void delete(RequirementClass u) {
        AppDatabase.databaseWriteExecutor.submit(() ->
                mRequirementClassDao.delete(u));
    }

}
