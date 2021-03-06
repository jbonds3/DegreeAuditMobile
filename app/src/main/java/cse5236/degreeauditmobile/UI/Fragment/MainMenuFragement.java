package cse5236.degreeauditmobile.UI.Fragment;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import androidx.lifecycle.ViewModelProvider;
import cse5236.degreeauditmobile.Model.Class;
import cse5236.degreeauditmobile.Model.Semester;
import cse5236.degreeauditmobile.Model.ViewModel.SemestersViewModel;
import cse5236.degreeauditmobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragement#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragement extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mUsername;
    private SemestersViewModel mSemestersViewModel;

    public MainMenuFragement() {
        super(R.layout.fragment_semesters_and_classes_page);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuFragement.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuFragement newInstance(String param1, String param2, String username) {
        MainMenuFragement fragment = new MainMenuFragement();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString("username", username);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            mUsername = getArguments().getString("username");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v;
        Activity activity = requireActivity();

        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            v = inflater.inflate(R.layout.fragment_semesters_and_classes_page_land, container, false);
        } else {
            v = inflater.inflate(R.layout.fragment_semesters_and_classes_page, container, false);
        }
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        mSemestersViewModel = new ViewModelProvider(this).get(SemestersViewModel.class);

        TextView semesterMMTV = getView().findViewById(R.id.semesterMMTV);
        TextView classMMTV = getView().findViewById(R.id.classMMTV);

        Button prevBtn = getView().findViewById(R.id.previousBtnMM);
        prevBtn.setClickable(false);
        prevBtn.setEnabled(false);

        Button nextBtn = getView().findViewById(R.id.nextBtnMM);

        mSemestersViewModel.getSemestersByUsername(mUsername).observe(getViewLifecycleOwner(), semesters -> {

            if (semesters.size() > 0) {
                ListIterator<String> semesterIT = semesters.stream().map(Semester::getSemesterID).collect(Collectors.toList()).listIterator();

                semesterMMTV.setText(semesterIT.next());

                if (!semesterIT.hasNext()) {
                    nextBtn.setClickable(false);
                    nextBtn.setEnabled(false);
                }

                mSemestersViewModel.getSemClasses(semesters.get(0)).observe(getViewLifecycleOwner(), classes -> {
                    if (classes.size() > 0) {
                        String classListStr = "";
                        for (Class c : classes) {
                            classListStr +=  c.title() + new String(new char[25 - c.title().length()]).replace('\0', ' ') + c.grade + "\n";
                        }
                        classMMTV.setText(classListStr);
                    } else {
                        classMMTV.setText("No Classes");
                    }
                });
            } else {
                semesterMMTV.setText("No Semesters");
                nextBtn.setClickable(false);
                nextBtn.setEnabled(false);
            }
        });


        //prevBTN
        prevBtn.setOnClickListener(v-> {
            mSemestersViewModel.getAllSemester().observe(getViewLifecycleOwner(), semesters -> {
                nextBtn.setEnabled(true);
                nextBtn.setClickable(true);

                ListIterator<String> semesterIT = semesters.stream().map(Semester::getSemesterID).collect(Collectors.toList()).listIterator();

                String currSemIT = semesterIT.next();
                String currSem = semesterMMTV.getText().toString();
                int currSemIndex = 0;
                while (semesterIT.hasNext() && !currSemIT.equals(currSem)) {
                    currSemIT = semesterIT.next();
                    currSemIndex++;
                }

                if (semesterIT.hasPrevious()) {
                    semesterIT.previous();
                    currSemIndex--;

                    if (semesterIT.hasPrevious()) {
                        semesterMMTV.setText(semesterIT.previous());
                    }
                } else {
                    semesterMMTV.setText("what happened here!!!!");
                }

                if (!semesterIT.hasPrevious()) {
                    prevBtn.setEnabled(false);
                    prevBtn.setClickable(false);
                }

                mSemestersViewModel.getSemClasses(semesters.get(currSemIndex)).observe(getViewLifecycleOwner(), classes -> {
                    if (classes.size() > 0) {
                        String classStr = "";
                        for (Class c : classes) {
                            classStr += c.title() + " " + c.grade + "\n";
                        }
                        classMMTV.setText(classStr);
                    } else {
                        classMMTV.setText("No Classes");
                    }
                });
            });
        });

        //nextBtn
        nextBtn.setOnClickListener(v-> {
            mSemestersViewModel.getAllSemester().observe(getViewLifecycleOwner(), semesters -> {
                prevBtn.setEnabled(true);
                prevBtn.setClickable(true);

                ListIterator<String> semesterIT = semesters.stream().map(Semester::getSemesterID).collect(Collectors.toList()).listIterator();

                String currSem = semesterIT.next();
                int currSemIndex = 0;
                while (semesterIT.hasNext() && !currSem.equals(semesterMMTV.getText().toString())) {
                    currSem = semesterIT.next();
                    currSemIndex++;
                }

                if (semesterIT.hasNext()) {
                    semesterMMTV.setText(semesterIT.next());
                    currSemIndex++;
                }

                if (!semesterIT.hasNext()) {
                    nextBtn.setEnabled(false);
                    nextBtn.setClickable(false);
                }

                mSemestersViewModel.getSemClasses(semesters.get(currSemIndex)).observe(getViewLifecycleOwner(), classes -> {
                    if (classes.size() > 0) {
                        String classStr = "";
                        for (Class c : classes) {
                            classStr += c.title() + " " + c.grade + "\n";
                        }
                        classMMTV.setText(classStr);
                    } else {
                        classMMTV.setText("No Classes");
                    }
                });
            });
        });

    }

}