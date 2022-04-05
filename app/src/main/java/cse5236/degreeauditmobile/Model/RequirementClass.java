package cse5236.degreeauditmobile.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(primaryKeys = {"requirement", "className"})
public
class RequirementClass {
    @NonNull
    public String requirement;

    @NonNull
    public String className;

    public RequirementClass(String requirement, String className) {
        this.requirement = requirement;
        this.className = className;
    }

}