package cse5236.degreeauditmobile.UI.DAObjects;

import android.os.Build;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;

import static java.lang.Double.NaN;


public class Class {

    private final String department;
    private final String courseNumber;
    private int credit;
    private List<Class> prereqs;
    private double grade = NaN;

    //requirement satisfied
//    private final boolean engrCoreReq;
//    private final boolean majorReq;
//    private final boolean geReq;
//    private final boolean teReq;

    public Class(String department, String courseNumber, List<Class> prereqs, int credit, double grade) {
        this.department = department;
        this.courseNumber = courseNumber;
        this.credit = credit;
        this.prereqs = prereqs;
//        this.engrCoreReq = engrCoreReq;
//        this.majorReq = majorReq;
//        this.geReq = geReq;
//        this.teReq = teReq;
    }

    public Class(String department, String courseNumber) {
        this.department = department;
        this.courseNumber = courseNumber;

        if (this.department.equals("CSE") && this.courseNumber.equals("1223")) {
            this.credit = 3;
            this.prereqs = null;
        } else if (this.department.equals("CSE") && this.courseNumber.equals("2221")) {
            this.credit = 4;
            this.prereqs = null;
        } else {
            this.credit = 0;
            this.prereqs = null;
        }

    }

    public String getDepartment() {
        return department;
    }

    public String getCourseNumber() {
        return courseNumber;
    }

    public List<Class> getPrereqs() {
        return prereqs;
    }

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
