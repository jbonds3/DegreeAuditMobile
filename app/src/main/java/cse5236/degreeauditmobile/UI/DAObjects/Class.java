package cse5236.degreeauditmobile.UI.DAObjects;

import java.util.List;

public class Class {

    private final String department;
    private final String courseNumber;
    private final int credit;
    private final List<Class> prereqs;
    private final double grade;

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
        this.grade = grade;
//        this.engrCoreReq = engrCoreReq;
//        this.majorReq = majorReq;
//        this.geReq = geReq;
//        this.teReq = teReq;
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

}
