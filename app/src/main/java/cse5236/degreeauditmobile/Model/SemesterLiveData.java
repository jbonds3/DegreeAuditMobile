package cse5236.degreeauditmobile.Model;

import androidx.lifecycle.MutableLiveData;

public class SemesterLiveData {

    private MutableLiveData<Semester> mCurrSem;

    public SemesterLiveData(Semester sem) {
        mCurrSem.setValue(sem);
    }

    public String getSession() {
        return mCurrSem.getValue().getSession();
    }
}
