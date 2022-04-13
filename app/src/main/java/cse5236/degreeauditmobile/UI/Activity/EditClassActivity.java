package cse5236.degreeauditmobile.UI.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import cse5236.degreeauditmobile.Model.Class;
import cse5236.degreeauditmobile.Model.Semester;
import cse5236.degreeauditmobile.Model.ViewModel.SemestersViewModel;
import cse5236.degreeauditmobile.R;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import java.util.stream.Collectors;

public class EditClassActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SemestersViewModel mSemestersViewModel;
    private String mAcademicYearText;
    private ArrayAdapter<CharSequence> editDeleteClassAdapter;
    private String mUsername;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        Bundle bundle = getIntent().getExtras();
        mAcademicYearText = bundle.getString("SEMESTERPID");
        mUsername = bundle.getString("USERNAME");


        mSemestersViewModel = new ViewModelProvider(this).get(SemestersViewModel.class);
        TextView editClassDisplayTV = findViewById(R.id.editDisplayClassesTV);

        mSemestersViewModel.getSemestersByUsername(mUsername).observe(this, semesters -> {
            Semester currSem = null;

            for (Semester sem : semesters) {
                if (sem.getSemesterID().equals(mAcademicYearText)) {
                    currSem = sem;
                }
            }

            if (currSem != null) {
                mSemestersViewModel.getSemClasses(currSem).observe(this, classes -> {
                    String classList = "";
                    for (Class c : classes) {
                        classList += c.title() + "\n";
                    }
                    editClassDisplayTV.setText(classList);
                });
            } else {
                editClassDisplayTV.setText("No Semester Available");
            }
        });

        Spinner editClassSpinner = (Spinner) findViewById(R.id.editClassList);

        //spinner adapters
        editDeleteClassAdapter = ArrayAdapter.createFromResource(this, R.array.CSECourses_array, android.R.layout.simple_spinner_item);
//
//        editDeleteClassAdapter.clear();
//        editDeleteClassAdapter.addAll(mSemestersViewModel.getSemClasses(currSemester).getValue().stream().map(Class::));

        editDeleteClassAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        editClassSpinner.setAdapter(editDeleteClassAdapter);
        editClassSpinner.setOnItemSelectedListener(this);

        Button editDeleteClassButton = findViewById(R.id.editDeleteClass);
        editDeleteClassButton.setOnClickListener(v -> {
            String classToDeleteStr = editClassSpinner.getSelectedItem().toString();
            Class classToDelete = new Class(classToDeleteStr, mAcademicYearText);
            mSemestersViewModel.delete(classToDelete);
            Intent intent = getIntent();
            finish();
            intent.putExtra("username", mUsername);
            startActivity(intent);
        });

        Button editAddClassButton = findViewById(R.id.editAddClass);
        editAddClassButton.setOnClickListener(v -> {
            String classToDeleteStr = editClassSpinner.getSelectedItem().toString();
            Class classToAdd = new Class(classToDeleteStr, mAcademicYearText);
            mSemestersViewModel.insert(classToAdd);
            Intent intent = getIntent();
            finish();
            intent.putExtra("username", mUsername);
            startActivity(intent);
        });

        Button editNextPage = findViewById(R.id.editNextPage);
        editNextPage.setOnClickListener(v -> {
            Intent intent = new Intent(EditClassActivity.this, MainMenuActivity.class);
            intent.putExtra("username", mUsername);
            startActivity(intent);
        });

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
//        this. parent.getItemAtPosition(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
//        System.out.println("h");
    }
}