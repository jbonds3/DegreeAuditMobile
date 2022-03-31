package cse5236.degreeauditmobile.Model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import java.util.UUID;

@Entity
public class Class {

    @PrimaryKey @NonNull
    public String courseID;

    @ColumnInfo(name = "Department")
    public String department;

    @ColumnInfo(name = "CourseNumber")
    public String courseNumber;

    @ColumnInfo(name = "Credit")
    public int credit;

//    @ColumnInfo(name = "Prerequisites")
//    public List<Class> prereqs;

    @ColumnInfo(name = "Grade")
    public double grade;

    public Class(String department, String courseNumber) {
        this.courseID = department + courseNumber;
        this.department = department;
        this.courseNumber = courseNumber;

        if (this.department.equals("CSE") && this.courseNumber.equals("1223")) {
            this.credit = 3;
//            this.prereqs = null;
        } else if (this.department.equals("CSE") && this.courseNumber.equals("2221")) {
            this.credit = 4;
//            this.prereqs = null;
        } else {
            this.credit = 0;
//            this.prereqs = null;
        }

    }

    public String getDepartment() {
        return department;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

//    public List<Class> getPrereqs() {
//        return prereqs;
//    }

    public int getCredit() {
        return credit;
    }

    public double getGrade() {
        return grade;
    }

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