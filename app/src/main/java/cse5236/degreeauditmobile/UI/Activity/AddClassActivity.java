package cse5236.degreeauditmobile.UI.Activity;

import androidx.appcompat.app.AppCompatActivity;
import cse5236.degreeauditmobile.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class AddClassActivity extends AppCompatActivity implements OnItemSelectedListener{
    private static final String TAG = "THE AddClass Activity";
    private TextView academicYearTV;
    private String academicYearText = "";
    private Spinner departmentSpinner;
    private ArrayAdapter<CharSequence> departmentAdapter;
    private Spinner courseNumberSpinner;
    private ArrayAdapter<CharSequence> CSECourseNumberAdapter;
    private Button addClassToSemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);
        Log.d(TAG, "onCreate() called");

        Bundle bundle = getIntent().getExtras();
        academicYearText += bundle.getString("SESSION") + bundle.getString("YEAR");

        academicYearTV = findViewById(R.id.academicYearClassTV);
        academicYearTV.setText(academicYearText);

        //spinner views
        departmentSpinner = (Spinner) findViewById(R.id.departmentSpinner);
        courseNumberSpinner = (Spinner) findViewById(R.id.courseNumberSpinner);

        //spinner adapter
        departmentAdapter = ArrayAdapter.createFromResource(this, R.array.department_array, android.R.layout.simple_spinner_item);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CSECourseNumberAdapter = ArrayAdapter.createFromResource(this, R.array.CSECourseNumber_array, android.R.layout.simple_spinner_item);

        //set spinner adapters
        departmentSpinner.setAdapter(departmentAdapter);
        departmentSpinner.setOnItemSelectedListener(this);

        courseNumberSpinner.setOnItemSelectedListener(this);

        courseNumberSpinner.setEnabled(false);
        courseNumberSpinner.setClickable(false);

        addClassToSemBtn = findViewById(R.id.addClassToSem);

//        addClassToSemBtn.setEnabled(false);
//        addClassToSemBtn.setClickable(false);

        addClassToSemBtn.setOnClickListener(v -> {
            departmentSpinner.setSelection(0);
            courseNumberSpinner.setSelection(0);
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        if (parent.getItemAtPosition(pos).toString().equals("CSE")) {
            courseNumberSpinner.setEnabled(true);
            courseNumberSpinner.setClickable(true);

            Log.d(TAG, departmentSpinner.getSelectedItem().toString());

            CSECourseNumberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            courseNumberSpinner.setAdapter(CSECourseNumberAdapter);
        }
        else if (parent.getItemAtPosition(pos).toString().equals("Major")) {
            courseNumberSpinner.setEnabled(false);
            courseNumberSpinner.setClickable(false);
            courseNumberSpinner.setSelection(0);
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
//        if (parent.getItemAtPosition(pos).toString().equals("Major")) {
//            courseNumberSpinner.setEnabled(false);
//            courseNumberSpinner.setClickable(false);
//            parent.setSelection(0);
//        }
    }
}