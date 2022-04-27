package cse5236.degreeauditmobile.Model;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.UUID;

@Entity(primaryKeys = {"courseID", "SemesterParentID", "ParentUsername"})
public class Class {

    @NonNull
    @ColumnInfo(name = "courseID")
    public String courseID;

    @ColumnInfo(name = "Department")
    public String department;

    @ColumnInfo(name = "CourseNumber")
    public String courseNumber;

    @ColumnInfo(name = "Credit")
    public int credit;

    @NonNull
    @ColumnInfo(name = "SemesterParentID")
    public String semesterParentID;

    @NonNull
    @ColumnInfo(name = "ParentUsername")
    public String parentUsername;

//    @ColumnInfo(name = "Prerequisites")
//    public List<Class> prereqs;

   @ColumnInfo(name = "Grade")
    public String grade;

    public Class(String department, String courseNumber, String semesterParentID, String parentUsername, String grade) {
        this.courseID = department + courseNumber;
        this.department = department;
        this.courseNumber = courseNumber;
        this.semesterParentID = semesterParentID;
        this.parentUsername = parentUsername;
        this.grade = grade;

        String[] creditPrereqsArray = ClassID.classID(this.department, this.courseNumber);
        this.credit = Integer.parseInt(creditPrereqsArray[0]);

    }

//    @RequiresApi(api = Build.VERSION_CODES.O)
    public Class(String title, String semesterParentID, String username, String grade) {
        String[] titleStr = title.split(" ");
        this.department = titleStr[0];
        this.courseNumber = titleStr[1];
        this.courseID = titleStr[0] + titleStr[1];
        this.semesterParentID = semesterParentID;
        this.parentUsername = username;
        this.grade = grade;

        String[] creditPrereqsArray = ClassID.classID(this.department, this.courseNumber);
        this.credit = Integer.parseInt(creditPrereqsArray[0]);

    }

    public String getCourseID() {return courseID;}

    public String getDepartment() {
        return department;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public String getSemesterParentID() {return semesterParentID;}

//    public List<Class> getPrereqs() {
//        return prereqs;
//    }

//    public int getCredit() {
//        return credit;
//    }

//    public double getGrade() {
//        return grade;
//    }

    public String title() {
        return department + " " + courseNumber;
    }

    @Override
    public boolean equals(Object obj) {
        boolean retVal = false;

        if (obj instanceof Class) {
            Class c = (Class) obj;
            retVal = (c.department.equals(this.department) && c.courseNumber.equals(this.courseNumber));
        }

        return retVal;
    }

}