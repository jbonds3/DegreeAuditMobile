package cse5236.degreeauditmobile.Model;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import cse5236.degreeauditmobile.Model.Class;

import cse5236.degreeauditmobile.Model.ClassListConverter;

@Entity(tableName = "Semester", primaryKeys = {"SemesterID", "username"})
public class Semester {

    @NonNull
    @ColumnInfo(name = "SemesterID")
    public String semesterID;

    @ColumnInfo(name = "Session")
    public String session;

    @ColumnInfo(name = "Year")
    public String year;

    @NonNull
    @ColumnInfo(name = "username")
    public String username;

    @TypeConverters(ClassListConverter.class)
    private List<Class> classes;


    public Semester(String session, String year, List<Class> classes, String username) {
        this.semesterID = session+year;
        this.session = session;
        this.year = year;
        this.classes = (classes);
        this.username = username;
    }

    public String getSemesterID() {
        return semesterID;
    }

    public String getSession() {
        return session;
    }

    public String getYear() {
        return year;
    }

    public List<Class> getClasses() {
        return classes;
    }

//    public String getClassesFromSemesterID() { return classTableID;}
}
