package cse5236.degreeauditmobile.UI.Activity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import cse5236.degreeauditmobile.Model.Semester;
import cse5236.degreeauditmobile.Model.ViewModel.SemestersViewModel;
import cse5236.degreeauditmobile.R;

public class EditSemesterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private static final String TAG = "THE EditSem Activity";
    private Button editSemToAddClassBtn;
    private Spinner sessionSpinner;
    private ArrayAdapter<CharSequence> sessionAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter<CharSequence> yearAdapter;
    private SemestersViewModel mSemestersViewModel;
    private String mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_semester);
        Log.d(TAG, "onCreate() called");

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("username")) {
            mUsername = myIntent.getStringExtra("username");
        } else {
            mUsername = "User";
        }

        //spinners views
        sessionSpinner = (Spinner) findViewById(R.id.editSessionSpinner);
        yearSpinner = (Spinner) findViewById(R.id.editYearSpinner);

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
        TextView addSemDis = findViewById(R.id.editSemTVDisplay);
        mSemestersViewModel.getSemestersByUsername(mUsername).observe(this, semesters -> {
            String s = "";
            for (Semester semt : semesters) {
                s += semt.semesterID + "\n";
            }
            addSemDis.setText(s);

        });

        editSemToAddClassBtn = findViewById(R.id.editSemToAddClassBtn);
        editSemToAddClassBtn.setOnClickListener(v -> {
            Intent editClassIntent = new Intent(EditSemesterActivity.this,EditClassActivity.class);
            Bundle extras = new Bundle();
            extras.putString("SESSION", sessionSpinner.getSelectedItem().toString());
            extras.putString("YEAR", yearSpinner.getSelectedItem().toString());
            extras.putString("SEMESTERPID", sessionSpinner.getSelectedItem().toString()+yearSpinner.getSelectedItem().toString());
            extras.putString("USERNAME", mUsername);
            editClassIntent.putExtras(extras);
            startActivity(editClassIntent);
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//        this. parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
//        System.out.println("h");
    }
}
