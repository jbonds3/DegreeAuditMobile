package cse5236.degreeauditmobile.Model;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import cse5236.degreeauditmobile.Model.Semester;

public class SemestersLiveData extends ViewModel {

    private List<SemesterLiveData> mSemList;

    public void addSemester(SemesterLiveData sem) {
        mSemList.add(sem);
    }
}