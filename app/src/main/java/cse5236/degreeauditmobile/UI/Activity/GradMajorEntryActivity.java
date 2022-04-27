package cse5236.degreeauditmobile.UI.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import cse5236.degreeauditmobile.Model.Class;
import cse5236.degreeauditmobile.Model.Helper;
import cse5236.degreeauditmobile.Model.Semester;
import cse5236.degreeauditmobile.Model.ViewModel.SemestersViewModel;
import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.UI.Graduatable;

public class GradMajorEntryActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String TAG = "THE GM Entry Activity";
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
        Log.d(TAG, "onCreate() called");

        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            setContentView(R.layout.fragment_grad_major_entry_land);
        } else {
            setContentView(R.layout.activity_grad_major_entry);
        }

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("username")) {
            mUsername = myIntent.getStringExtra("username");
        } else {
            mUsername = "User";
        }

        //navigation btn to Add Class Activity
        addSemToAddClassBtn = (Button) findViewById(R.id.addSemToAddClassBtn);

//        //spinners views
//        sessionSpinner = (Spinner) findViewById(R.id.sessionSpinner);
//        yearSpinner = (Spinner) findViewById(R.id.yearSpinner);
//
//        //spinner adapters
//        sessionAdapter = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_spinner_item);
//
//        yearAdapter = ArrayAdapter.createFromResource(this, R.array.specialization_array, android.R.layout.simple_spinner_item);
//
//        sessionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        sessionSpinner.setAdapter(sessionAdapter);
//        yearSpinner.setAdapter(yearAdapter);
//
//        sessionSpinner.setOnItemSelectedListener(this);
//        yearSpinner.setOnItemSelectedListener(this);

        mSemestersViewModel = new ViewModelProvider(this).get(SemestersViewModel.class);
        TextView addSemDis = findViewById(R.id.addSemTVDisplay);
        mSemestersViewModel.getSemestersByUsername(mUsername).observe(this, semesters -> {
            String preS = "No \n\nNo";
            addSemDis.setText(preS);
            for (Semester sem : semesters) {
                mSemestersViewModel.getSemClasses(sem).observe(this, classes -> {
                    String s = (String) addSemDis.getText();

                    double credits = 0;
                    double gpa = 0;
                    for (int k = 0; k < classes.size(); k++) {
                        credits += classes.get(k).credit;
                        gpa += classes.get(k).credit * Helper.classLetterToNumber(classes.get(k).grade);
                    }

                    for (Class c : classes) {

                        if (c.getCourseID().equals("CSE2221")) {
                            if (Graduatable.userCanGrad(true, gpa, credits).equals("Student can graduate")) {
                                s = "Yes \n\n Yes";
                            } else if (!s.equals("Yes \n\nYes") && Graduatable.userCanMajor(true, gpa).equals("Student can apply")) {
                                s = "Yes \n\nNo";
                            }
                        }
                    }

                    if (!s.equals("Yes \n\nYes") && !s.equals("No \n\nNo")) {
                        addSemDis.setText(s);
                    }
                });
            }
        });




        addSemToAddClassBtn.setOnClickListener(v -> {

            Intent mainMenuIntent = new Intent(GradMajorEntryActivity.this,MainMenuActivity.class);
            Bundle extras = new Bundle();
            extras.putString("username", mUsername);
            mainMenuIntent.putExtras(extras);
            startActivity(mainMenuIntent);

        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//        this. parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
//        System.out.println("h");
    }
}