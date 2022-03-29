package cse5236.degreeauditmobile.UI.Activity;

import android.database.DataSetObserver;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.UI.MyExpandableListAdapter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_progress);
        Log.d(TAG, "onCreate() called");

        createGroupList();
        mCheckProgressCollection = new HashMap<>();
        String[] x = {"MPHR", "CPHR"};
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

}