package cse5236.degreeauditmobile.UI.DAObjects;

import java.util.List;
import cse5236.degreeauditmobile.Model.Class;

public class Semester {

    private final String session;
    private final int year;
    private final List<Class> classes;

    public Semester(String session, int year, List<Class> classes) {
        this.session = session;
        this.year = year;
        this.classes = classes;
    }

    public String getSession() {
        return session;
    }

    public int getYear() {
        return year;
    }

    public List<Class> getClasses() {
        return classes;
    }
}
