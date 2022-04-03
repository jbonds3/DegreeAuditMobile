package cse5236.degreeauditmobile.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import cse5236.degreeauditmobile.Model.ViewModel.SemestersViewModel;
import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.Model.Class;
import cse5236.degreeauditmobile.Model.Semester;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AddClassActivity extends AppCompatActivity {
    private static final String TAG = "THE AddClass Activity";
    private String mSession;
    private String mYear;
    private TextView mAcademicYearTV;
    private String mAcademicYearText = "";
    private Spinner mDepartmentSpinner;
    private ArrayAdapter<CharSequence> mDepartmentAdapter;
    private Spinner mCourseNumberSpinner;
    private ArrayAdapter<CharSequence> mCSECourseNumberAdapter;
    private Button mAddClassToSemBtn;
    private Button mBackToMainMenuBtn;
    private List<Class> mClassList;
    private Semester mSemester;
    private SemestersViewModel mSemestersViewModel;
    private MutableLiveData<Semester> currSem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        Log.d(TAG, "onCreate() called");

        Bundle bundle = getIntent().getExtras();
        mSession = bundle.getString("SESSION");
        mYear = bundle.getString("YEAR");
        mAcademicYearText += bundle.getString("SESSION") + bundle.getString("YEAR");

        mAcademicYearTV = findViewById(R.id.academicYearClassTV);
        mAcademicYearTV.setText(mAcademicYearText);

        //spinner, btn views
        mDepartmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
        mCourseNumberSpinner = (Spinner) findViewById(R.id.courseNumberSpinner);
        mAddClassToSemBtn = findViewById(R.id.addClassToSemBtn);
        mBackToMainMenuBtn = findViewById(R.id.backToMainMenuBtn);

        //department spinner adapter
        mDepartmentAdapter = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_spinner_item);
        mDepartmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //set department spinner adapter
        mDepartmentSpinner.setAdapter(mDepartmentAdapter);

        //set department spinner listener
        mDepartmentSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (parent.getItemAtPosition(pos).toString().equals("CSE")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCSECourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.CSECourseNumber_array, android.R.layout.simple_spinner_item);
                    mCSECourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCSECourseNumberAdapter);
                }
                else if (parent.getItemAtPosition(pos).toString().equals("Major")) {
                    mCourseNumberSpinner.setSelection(0);
                    mCourseNumberSpinner.setEnabled(false);
                    mCourseNumberSpinner.setClickable(false);
                    mAddClassToSemBtn.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //set course Number spinner listener
        mCourseNumberSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if (parent.getItemAtPosition(pos).toString().equals("Course Number")) {
                    mAddClassToSemBtn.setEnabled(false);
                    mAddClassToSemBtn.setClickable(false);
                }
                else {
                    mAddClassToSemBtn.setEnabled(true);
                    mAddClassToSemBtn.setClickable(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //disable course number spinner and add class btn
        mCourseNumberSpinner.setEnabled(false);
        mCourseNumberSpinner.setClickable(false);

        mAddClassToSemBtn.setEnabled(false);
        mAddClassToSemBtn.setClickable(false);

        mClassList = new ArrayList<>();

        LinearLayout l = (LinearLayout) findViewById(R.id.LLinSV);

        mSemestersViewModel = new ViewModelProvider(this).get(SemestersViewModel.class);

        mAddClassToSemBtn.setOnClickListener(v -> {
            Class classToAdd = new Class(mDepartmentSpinner.getSelectedItem().toString(), mCourseNumberSpinner.getSelectedItem().toString(), mAcademicYearText);
//            Log.d(TAG, String.valueOf(classToAdd.getCredit()));

            if (!mClassList.contains(classToAdd)) {
                mClassList.add(classToAdd);
                Toast.makeText(AddClassActivity.this, classToAdd.title() + " was added", Toast.LENGTH_SHORT).show();
                TextView valueTV = new TextView(this);
                valueTV.setText(classToAdd.title());
                valueTV.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));

                l.addView(valueTV);
            } else {
                Toast.makeText(AddClassActivity.this, classToAdd.title() + " is already added", Toast.LENGTH_SHORT).show();
            }
            mDepartmentSpinner.setSelection(0);
            mCourseNumberSpinner.setSelection(0);


            mSemestersViewModel.insert(classToAdd);

        });


        mBackToMainMenuBtn.setOnClickListener(v -> {
            Intent addClassIntent = new Intent(AddClassActivity.this, MainMenuActivity.class);
            mSemester = new Semester(mSession, mYear, mClassList);
////            addClassIntent.putExtra("SEMESTER", mSemester);
//
//            if (mSemester.equals(null)) {
//                addClassIntent.putExtra("SEMESTER", "eNull");
//            } else if (mSemester == null) {
//                addClassIntent.putExtra("SEMESTER", "rNull");
//            } else {
//                mSemestersViewModel.setSemester(mSemester);
//
//                addClassIntent.putExtra("SEMESTER", mSemestersViewModel.getFirstSem().getValue().getSession());
//            }

//            addClassIntent.putExtra("SEMESTER", String.valueOf(t));

            startActivity(addClassIntent);
        });
    }
}