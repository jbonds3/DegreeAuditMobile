package cse5236.degreeauditmobile.Model.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cse5236.degreeauditmobile.Model.ProgressRequirements;
import cse5236.degreeauditmobile.Model.ReqToClassRepo;
import cse5236.degreeauditmobile.Model.RequirementClass;
import cse5236.degreeauditmobile.Model.RequirementRepo;

public class RequirementViewModel extends AndroidViewModel {
    private static final String TAG = "THE ReqClassVM";

    private RequirementRepo mRequirementRepo;
    private LiveData<List<ProgressRequirements>> mAllRequirements;

    public RequirementViewModel(@NonNull Application application) {
        super(application);
        mRequirementRepo = new RequirementRepo(application);
        mAllRequirements = mRequirementRepo.getAllRequirements();
    }

    public LiveData<List<ProgressRequirements>> getAll() {
        return mAllRequirements;
    }

    public boolean contains(String requirement) {return mRequirementRepo.contains(requirement);}

    public void insert(ProgressRequirements req) {
        mRequirementRepo.insert(req);
    }

}