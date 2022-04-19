package cse5236.degreeauditmobile.UI.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;

import cse5236.degreeauditmobile.Model.Helper;
import cse5236.degreeauditmobile.Model.ProgressRequirements;
import cse5236.degreeauditmobile.Model.RequirementClass;
import cse5236.degreeauditmobile.Model.ViewModel.ReqToClassViewModel;
import cse5236.degreeauditmobile.Model.ViewModel.RequirementViewModel;
import cse5236.degreeauditmobile.Model.ViewModel.SemestersViewModel;
import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.Model.Class;

public class CheckProgressActivity extends AppCompatActivity {
    private static final String TAG = "CHECK_PROGRESS_ACTIVITY";
    //List for group items
    private List<String> mGroupList;

    //List for children items
    private List<String> mChildList;

    //check progress collection
    private Map<String, String[]> mCheckProgressCollection;

    private ExpandableListView checkProgressELV;
    private ExpandableListAdapter checkProgressADP;
    private Button mUpdateRequirementsButton;
    private TableLayout mRequirementsTable;
    private SemestersViewModel mSemestersViewModel;
    private ReqToClassViewModel mReqToClassViewModel;
    private RequirementViewModel mRequirementViewModel;
    private String mUsername;
    private TableLayout mGPATable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_progress);
        Log.d(TAG, "onCreate() called");

        mRequirementViewModel = new ViewModelProvider(this).get(RequirementViewModel.class);
        mReqToClassViewModel = new ViewModelProvider(this).get(ReqToClassViewModel.class);
        mSemestersViewModel = new ViewModelProvider(this).get(SemestersViewModel.class);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("username")) {
            mUsername = myIntent.getStringExtra("username");
        } else {
            mUsername = "User";
        }

        mGPATable = findViewById(R.id.gpaTable);

        mSemestersViewModel.getSemestersByUsername(mUsername).observe(this, semesters -> {
            List<Class> completed = new ArrayList<>();
            int count[] = new int[1];
            count[0] = 0;
            for (int m = 0; m < semesters.size(); m++) {
                mSemestersViewModel.getSemClasses(semesters.get(m)).observe(this, classes -> {
                    completed.addAll(classes);
                    count[0] = count[0] + 1;
                    if (count[0] == semesters.size()) {
                        double credits = 0;
                        double major_credits = 0;
                        double major_gpa = 0;
                        double gpa = 0;
                        for (int k = 0; k < completed.size(); k++) {
                            credits += completed.get(k).credit;
                            gpa += completed.get(k).credit * Helper.classLetterToNumber(completed.get(k).grade);
                            if (completed.get(k).department.equals("CSE")) {
                                major_credits += completed.get(k).credit;
                                major_gpa += completed.get(k).credit * Helper.classLetterToNumber(completed.get(k).grade);
                            }
                        }
                        final DecimalFormat df = new DecimalFormat("0.00");
                        gpa = gpa/credits;
                        major_gpa = major_gpa/major_credits;
                        TableRow row = (TableRow) mGPATable.getChildAt(0);
                        TextView creditsTV = (TextView) row.getChildAt(1);
                        creditsTV.setText(Double.toString(credits));

                        row = (TableRow) mGPATable.getChildAt(1);
                        TextView totalTV = (TextView) row.getChildAt(1);
                        totalTV.setText(Double.toString(gpa));
                        totalTV.setText(df.format(gpa));

                        row = (TableRow) mGPATable.getChildAt(2);
                        TextView majorTV = (TextView) row.getChildAt(1);
                        majorTV.setText(Double.toString(gpa));
                        majorTV.setText(df.format(major_gpa));

                        final String positionToReq[] = new String[10];
                        LiveData<List<ProgressRequirements>> all_requirements_Live = mRequirementViewModel.getAll();
                        all_requirements_Live.observe(this, all_requirements-> {

                        for (int i = 0; i < all_requirements.size(); i++) {
                            String req_name = all_requirements.get(i).requirement;
                            positionToReq[i] = req_name;
                            TableLayout req_table = (TableLayout)getLayoutInflater().inflate(R.layout.table_requirement, null);
                            TableRow req_row = (TableRow) req_table.getChildAt(0);
                            TextView text = (TextView)req_row.getChildAt(0);
                            text.setText(req_name);
                            mRequirementsTable.addView(req_table);
                            LiveData<List<RequirementClass>> current_live = mReqToClassViewModel.findByRequirement(req_name);
                            current_live.observe(this, current -> {
                            for (int j = 0; j < current.size(); j++) {
                                String class_name = current.get(j).className;
                                String completion = "not completed yet";
                                String grade = "";
                                for (int k = 0; k < completed.size(); k++) {
                                    String full_name = completed.get(k).getDepartment() + " " + completed.get(k).getCourseNumber();
                                    if (full_name.equals(class_name)) {
                                        completion = "completed";
                                        grade = completed.get(k).grade;
                                        break;
                                    }
                                }
                                TableRow class_row = (TableRow)getLayoutInflater().inflate(R.layout.class_row, null);
                                TextView class_name_table = (TextView) class_row.getChildAt(0);
                                TextView completed_table = (TextView) class_row.getChildAt(1);
                                TextView grade_table = (TextView) class_row.getChildAt(2);
                                class_name_table.setText(class_name);
                                completed_table.setText(completion);
                                grade_table.setText(grade);
                                int table_number = Helper.findPosition(positionToReq, current.get(0).requirement);
                                TableLayout thatReq = (TableLayout)mRequirementsTable.getChildAt(table_number);
                                thatReq.addView(class_row);
                            }
                            });
                        }
                        });
                    }
                });
            }

        });

        mUpdateRequirementsButton = findViewById(R.id.populateRequirementsButton);
        mUpdateRequirementsButton.setOnClickListener(v -> {
            String req1 = "Fundamentals";
            String req2 = "Advanced";
            if (!mRequirementViewModel.contains(req1)) {
                ProgressRequirements req = new ProgressRequirements(req1);
                mRequirementViewModel.insert(req);
                RequirementClass reqclass1 = new RequirementClass(req1, "CSE 1223");
                RequirementClass reqclass2 = new RequirementClass(req1, "CSE 2221");
                mReqToClassViewModel.insert(reqclass1);
                mReqToClassViewModel.insert(reqclass2);
            }

            if (!mRequirementViewModel.contains(req2)) {
                ProgressRequirements req = new ProgressRequirements(req2);
                mRequirementViewModel.insert(req);
                RequirementClass reqclass1 = new RequirementClass(req2, "CSE 2321");
                mReqToClassViewModel.insert(reqclass1);
            }


        });

        mRequirementsTable = findViewById(R.id.requirementsTable);

/*
        createGroupList();
        mCheckProgressCollection = new HashMap<>();
        String[] x = {"Total:", "Major:"};
        mCheckProgressCollection.put("GPA", x);

        //ExpandableListView functions
        checkProgressELV = findViewById(R.id.checkProgressELV);
        checkProgressADP = new MyExpandableListAdapter(this, mGroupList, mCheckProgressCollection);
        checkProgressELV.setAdapter(checkProgressADP);
        checkProgressELV.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedPos = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPos != -1 && groupPosition != lastExpandedPos) {
                    checkProgressELV.collapseGroup(lastExpandedPos);
                }
                lastExpandedPos = groupPosition;
            }
        });
        checkProgressELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String child = checkProgressADP.getChild(groupPosition, childPosition).toString();
                return true;
            }
        });
    }

    private void createGroupList() {
        mGroupList = new ArrayList<>();
        mGroupList.add("GPA");
    }


 */

    }
}