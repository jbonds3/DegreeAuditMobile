package cse5236.degreeauditmobile.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import cse5236.degreeauditmobile.R;

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    //context
    private final Context mContext;

    //List for group items
    private final List<String> mGroupList;

    //check progress collection
    private final Map<String, String[]> mCheckProgressCollection;

    public MyExpandableListAdapter(Context context, List<String> groupList, Map<String, String[]> checkProgressCollection) {
        this.mContext = context;
        this.mGroupList = groupList;
        this.mCheckProgressCollection = checkProgressCollection;
    }

    @Override
    public int getGroupCount() {
        return mCheckProgressCollection.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mCheckProgressCollection.get(mGroupList.get(groupPosition)).length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mCheckProgressCollection.get(mGroupList.get(groupPosition))[childPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        String topTitle = getGroup(groupPosition).toString();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_check_progress_group_item, parent, false);
        }

        TextView groupItem = view.findViewById(R.id.groupItemTV);
        groupItem.setText(topTitle);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
        String child = getChild(groupPosition, childPosition).toString();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.fragment_check_progress_child_item, parent, false);
        }

        TextView childItem = view.findViewById(R.id.childItemTV);
        childItem.setText(child);

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
