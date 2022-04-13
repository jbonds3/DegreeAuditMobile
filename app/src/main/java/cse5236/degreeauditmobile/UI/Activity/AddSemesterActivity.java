package cse5236.degreeauditmobile.UI.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import cse5236.degreeauditmobile.Model.Semester;
import cse5236.degreeauditmobile.Model.Class;
import cse5236.degreeauditmobile.Model.ViewModel.SemestersViewModel;
import cse5236.degreeauditmobile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AddSemesterActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String TAG = "THE AddSem Activity";
    private Button addSemToAddClassBtn;
    private Spinner sessionSpinner;
    private ArrayAdapter<CharSequence> sessionAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter<CharSequence> yearAdapter;
    private SemestersViewModel mSemestersViewModel;
    private String mUsername;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);
        Log.d(TAG, "onCreate() called");

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("username")) {
            mUsername = myIntent.getStringExtra("username");
        } else {
            mUsername = "User";
        }

        //spinners views
        sessionSpinner = (Spinner) findViewById(R.id.sessionSpinner);
        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);

        //spinner adapters
        sessionAdapter = ArrayAdapter.createFromResource(this, R.array.session_array, android.R.layout.simple_spinner_item);

        yearAdapter = ArrayAdapter.createFromResource(this, R.array.year_array, android.R.layout.simple_spinner_item);

        sessionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sessionSpinner.setAdapter(sessionAdapter);
        yearSpinner.setAdapter(yearAdapter);

        sessionSpinner.setOnItemSelectedListener(this);
        yearSpinner.setOnItemSelectedListener(this);

        mSemestersViewModel = new ViewModelProvider(this).get(SemestersViewModel.class);
        TextView addSemDis = findViewById(R.id.addSemTVDisplay);
        mSemestersViewModel.getAllSemester().observe(this, semesters -> {
            String s = "";
            for (Semester semt : semesters) {
                s += semt.semesterID + "\n";
            }
            addSemDis.setText(s);

        });

        //navigation btn to Add Class Activity
        addSemToAddClassBtn = (Button) findViewById(R.id.addSemToAddClassBtn);

        addSemToAddClassBtn.setOnClickListener(v -> {
//            mSemestersViewModel.deleteAllClasses();

            List<Class> classList = new ArrayList<>();
            Semester semesterToAdd = new Semester(sessionSpinner.getSelectedItem().toString(), yearSpinner.getSelectedItem().toString(), classList, mUsername);


//            mSemestersViewModel.deleteAllSemesters();
//            mSemestersViewModel.deleteAllClasses();

            if (!mSemestersViewModel.containsSemester(semesterToAdd)) {
                mSemestersViewModel.insert(semesterToAdd);
                Intent addClassIntent = new Intent(AddSemesterActivity.this,AddClassActivity.class);
                Bundle extras = new Bundle();
                extras.putString("USERNAME", mUsername);
                extras.putString("SESSION", sessionSpinner.getSelectedItem().toString());
                extras.putString("YEAR", yearSpinner.getSelectedItem().toString());
                extras.putString("SEMESTERPID", sessionSpinner.getSelectedItem().toString()+yearSpinner.getSelectedItem().toString());
                addClassIntent.putExtras(extras);
                startActivity(addClassIntent);
            } else {
                Toast.makeText(AddSemesterActivity.this, "Semester Already Made", Toast.LENGTH_SHORT).show();
            }
//            mSemestersViewModel.getAllSemester().observe(this, semesters -> {
//                List<String> semIDList = semesters.stream().map(Semester::getSemesterID).collect(Collectors.toList());
//                if (!semIDList.contains(semesterToAdd.getSemesterID())) {
//                if (!mSemestersViewModel.containsSemester(semesterToAdd)) {
//                    mSemestersViewModel.insert(semesterToAdd);
//                    Intent addClassIntent = new Intent(AddSemesterActivity.this,AddClassActivity.class);
//                    Bundle extras = new Bundle();
//                    extras.putString("SESSION", sessionSpinner.getSelectedItem().toString());
//                    extras.putString("YEAR", yearSpinner.getSelectedItem().toString());
//                    extras.putString("SEMESTERPID", sessionSpinner.getSelectedItem().toString()+yearSpinner.getSelectedItem().toString());
//                    addClassIntent.putExtras(extras);
//                    startActivity(addClassIntent);
//                } else {
//                    Toast.makeText(AddSemesterActivity.this, "Semester Already Made", Toast.LENGTH_SHORT).show();
//                }
//            });

        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//        this. parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
//        System.out.println("h");
    }
}