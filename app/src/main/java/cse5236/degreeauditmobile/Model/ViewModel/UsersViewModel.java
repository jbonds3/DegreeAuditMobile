package cse5236.degreeauditmobile.Model.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import cse5236.degreeauditmobile.Model.Class;
import cse5236.degreeauditmobile.Model.ClassRepo;
import cse5236.degreeauditmobile.Model.Semester;
import cse5236.degreeauditmobile.Model.SemesterRepo;
import cse5236.degreeauditmobile.Model.User;
import cse5236.degreeauditmobile.Model.UserRepo;

public class UsersViewModel extends AndroidViewModel {
    private static final String TAG = "THE UserVM";

    private UserRepo mUserRepo;
    private LiveData<List<User>> mAllUsers;

    public UsersViewModel(@NonNull Application application) {
        super(application);
        mUserRepo = new UserRepo(application);
        mAllUsers = mUserRepo.getAllUsers();
    }

    public boolean contains(String username) {
        return mUserRepo.contains(username);
    }

    public LiveData<User> getUser(String username) {
        return mUserRepo.getUserByName(username);
    }

    public User getUserNow(String username) {
        return mUserRepo.getUserByNameNow(username);
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public void insert(User u) {
        mUserRepo.insert(u);
    }

    public void update(User u){mUserRepo.update(u);}

    public void delete(User u) {
        mUserRepo.delete(u);
    }

}