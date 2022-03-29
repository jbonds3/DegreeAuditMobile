package cse5236.degreeauditmobile.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import cse5236.degreeauditmobile.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.navigation.NavigationBarView;

public class AddSemesterActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String TAG = "THE AddSem Activity";
    private Button addSemToAddClassBtn;
    private Spinner sessionSpinner;
    private ArrayAdapter<CharSequence> sessionAdapter;
    private Spinner yearSpinner;
    private ArrayAdapter<CharSequence> yearAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_semester);
        Log.d(TAG, "onCreate() called");

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

        //navigation btn to Add Class Activity
        addSemToAddClassBtn = (Button) findViewById(R.id.addSemToAddClassBtn);

        addSemToAddClassBtn.setOnClickListener(v -> {
            Intent addClassIntent = new Intent(AddSemesterActivity.this,AddClassActivity.class);
            Bundle extras = new Bundle();
            extras.putString("SESSION", sessionSpinner.getSelectedItem().toString());
            extras.putString("YEAR", yearSpinner.getSelectedItem().toString());
            addClassIntent.putExtras(extras);
            startActivity(addClassIntent);
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//        this. parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
//        System.out.println("h");
    }
}