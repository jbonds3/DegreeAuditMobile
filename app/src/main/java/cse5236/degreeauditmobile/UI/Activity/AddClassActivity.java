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
    private ArrayAdapter<CharSequence> mCourseNumberAdapter;
    private Spinner mGradeSpinner;
    private ArrayAdapter<CharSequence> mGradeAdapter;
    private Button mAddClassToSemBtn;
    private Button mBackToMainMenuBtn;
    private List<Class> mClassList;
    private Semester mSemester;
    private SemestersViewModel mSemestersViewModel;
    private MutableLiveData<Semester> currSem;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        Log.d(TAG, "onCreate() called");

        Bundle bundle = getIntent().getExtras();
        mUsername = bundle.getString("username");
        mSession = bundle.getString("SESSION");
        mYear = bundle.getString("YEAR");
        mAcademicYearText += bundle.getString("SESSION") + bundle.getString("YEAR");

        mAcademicYearTV = findViewById(R.id.academicYearClassTV);
        mAcademicYearTV.setText(mAcademicYearText);

        //spinner, btn views
        mGradeSpinner = findViewById(R.id.gradeSpinner);
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

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.CSECourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("MATH")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.MATHCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("ENGR")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.ENGRCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("ENGLISH")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.ENGLISHCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("PHILOS")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.PHILOSCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("PHYSICS")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.PHYSICSCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("STAT")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.STATCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("EARTHSC")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.EARTHSCCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("COMM")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.COMMCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("RURALSOC")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.RURALSOCCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("COMPSTD")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.COMPSTDCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("AFAMAST")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.AFAMASTCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("ECE")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.ECECourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("THEATRE")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.THEATRECourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("LING")) {
                    mCourseNumberSpinner.setEnabled(true);
                    mCourseNumberSpinner.setClickable(true);

                    Log.d(TAG, mDepartmentSpinner.getSelectedItem().toString());

                    mCourseNumberAdapter = ArrayAdapter.createFromResource(AddClassActivity.this, R.array.LINGCourseNumber_array, android.R.layout.simple_spinner_item);
                    mCourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    mCourseNumberSpinner.setAdapter(mCourseNumberAdapter);
                } else if (parent.getItemAtPosition(pos).toString().equals("Major")) {
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

        //grade spinner adapter
        mGradeAdapter = ArrayAdapter.createFromResource(this, R.array.grade_array, android.R.layout.simple_spinner_item);
        mGradeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mGradeSpinner.setAdapter(mGradeAdapter);

        //disable course number spinner and add class btn
        mCourseNumberSpinner.setEnabled(false);
        mCourseNumberSpinner.setClickable(false);

        mAddClassToSemBtn.setEnabled(false);
        mAddClassToSemBtn.setClickable(false);

        mClassList = new ArrayList<>();

        LinearLayout l = (LinearLayout) findViewById(R.id.LLinSV);

        mSemestersViewModel = new ViewModelProvider(this).get(SemestersViewModel.class);

        mAddClassToSemBtn.setOnClickListener(v -> {
            Class classToAdd = new Class(mDepartmentSpinner.getSelectedItem().toString(), mCourseNumberSpinner.getSelectedItem().toString(), mAcademicYearText, mUsername, mGradeSpinner.getSelectedItem().toString());
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
            addClassIntent.putExtra("username", mUsername);
            mSemester = new Semester(mSession, mYear, mClassList, mUsername);
            startActivity(addClassIntent);
        });
    }
}