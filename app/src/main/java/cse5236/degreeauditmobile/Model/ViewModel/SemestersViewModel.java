package cse5236.degreeauditmobile.Model.ViewModel;

import android.app.Application;
import android.util.Log;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cse5236.degreeauditmobile.Model.AppDatabase;
import cse5236.degreeauditmobile.Model.Class;
import cse5236.degreeauditmobile.Model.ClassRepo;
import cse5236.degreeauditmobile.Model.Semester;
import cse5236.degreeauditmobile.Model.SemesterLiveData;
import cse5236.degreeauditmobile.Model.SemesterRepo;

public class SemestersViewModel extends AndroidViewModel {
    private static final String TAG = "THE SemVM";

    private ClassRepo mClassRepo;
    private LiveData<List<Class>> mAllClass;
    private SemesterRepo mSemesterRepo;
    private LiveData<List<Semester>> mAllSemester;

    public SemestersViewModel(@NonNull Application application) {
        super(application);
        mClassRepo = new ClassRepo(application);
        mAllClass = mClassRepo.getAllClass();

        mSemesterRepo = new SemesterRepo(application);
        mAllSemester = mSemesterRepo.getAllSemester();
    }

    public LiveData<Class> getClass(Class c) {
        return mClassRepo.getClassByTitle(c);
    }

    public LiveData<List<Class>> getAllClass() {
        return mAllClass;
    }

    public void insert(Class c) {
        mClassRepo.insert(c);
    }

    public void delete(Class c) {
        mClassRepo.delete(c);
    }

    public void deleteAllClasses() {mClassRepo.deleteAll();}

    public LiveData<Semester> getSemester(Semester semester) {
        return mSemesterRepo.getSemByID(semester);
    }

    public LiveData<List<Semester>> getAllSemester() {
        return mAllSemester;
    }

    public void insert(Semester semester) {
        mSemesterRepo.insert(semester);
    }

    public void delete(Semester semester) {
        mSemesterRepo.delete(semester);
    }

    public void deleteAllSemesters() {mSemesterRepo.deleteAll();}

    public boolean containsSemester(Semester semester) {
        boolean semesterInList = false;

        LiveData<Semester> semesterFromTable = mSemesterRepo.getSemByID(semester);
        Semester semesterFromTableValue = semesterFromTable.getValue();

        if (semesterFromTableValue != null && semesterFromTableValue.getSemesterID().equals(semester.getSemesterID())) {
            semesterInList = true;
        }

        return semesterInList;
    }

    public LiveData<List<Class>> getSemClasses(Semester semester) {
        return mSemesterRepo.getClassesBySemID(semester);
    }
}