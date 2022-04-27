package cse5236.degreeauditmobile.Model;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepo {

    private final UserDao mUserDao;
    private final LiveData<List<User>> mAllUsers;

    public UserRepo(Application application) {
        AppDatabase db = DatabaseSingleton.getDatabaseInstance("App_Database", application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllLive();
    }

    public LiveData<User> getUserByName(String username) {
        return mUserDao.findByNameLive(username);
    }

    public User getUserByNameNow(String username) {
        return mUserDao.findByName(username);
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }


    public void insert(User u) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                mUserDao.insertAll(u));
    }

    public void update(User u) {
        AppDatabase.databaseWriteExecutor.execute(() ->
                mUserDao.UpdateUser(u));
    }

    public void delete(User u) {
        AppDatabase.databaseWriteExecutor.submit(() ->
                mUserDao.delete(u));
    }

    public boolean contains(String username) {
        return mUserDao.hasEntry(username);
    }

}
