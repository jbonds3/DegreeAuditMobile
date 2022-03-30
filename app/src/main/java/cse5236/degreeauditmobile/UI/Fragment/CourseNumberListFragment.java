package cse5236.degreeauditmobile.UI.Fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cse5236.degreeauditmobile.R;

public class CourseNumberListFragment extends Fragment {

    private CourseNumberListViewModel mViewModel;

    public static CourseNumberListFragment newInstance() {
        return new CourseNumberListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course_number_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CourseNumberListViewModel.class);
        // TODO: Use the ViewModel
    }

}