package cse5236.degreeauditmobile.Model.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cse5236.degreeauditmobile.Model.ReqToClassRepo;
import cse5236.degreeauditmobile.Model.RequirementClass;
import cse5236.degreeauditmobile.Model.User;
import cse5236.degreeauditmobile.Model.UserRepo;

public class ReqToClassViewModel extends AndroidViewModel {
    private static final String TAG = "THE ReqClassVM";

    private ReqToClassRepo mReqToClassRepo;
    private LiveData<List<RequirementClass>> mAllReqToClass;

    public ReqToClassViewModel(@NonNull Application application) {
        super(application);
        mReqToClassRepo = new ReqToClassRepo(application);
        mAllReqToClass = mReqToClassRepo.getAllReqToClass();
    }

    public LiveData<List<RequirementClass>> getAll() {
        return mAllReqToClass;
    }

    public LiveData<List<RequirementClass>> findByRequirement(String requirement) {
        return mReqToClassRepo.findByRequirement(requirement);
    }

    public void insert(RequirementClass reqClass) {
        mReqToClassRepo.insert(reqClass);
    }

}