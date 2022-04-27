package cse5236.degreeauditmobile;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import cse5236.degreeauditmobile.Model.AppDatabase;
import cse5236.degreeauditmobile.Model.Class;
import cse5236.degreeauditmobile.Model.ClassDao;
import cse5236.degreeauditmobile.Model.DatabaseSingleton;
import cse5236.degreeauditmobile.Model.Semester;
import cse5236.degreeauditmobile.Model.SemesterDao;
import cse5236.degreeauditmobile.Model.User;
import cse5236.degreeauditmobile.Model.UserDao;
import cse5236.degreeauditmobile.UI.StringUtils;

@RunWith(AndroidJUnit4.class)
public class ClassSemesterFunctionalityTest {
    private SemesterDao semesterDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = DatabaseSingleton.getDatabaseInstance("Test_Database", context);
        semesterDao = db.semesterDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void addSemesterWithClasses() throws Exception {
        Class class1 = new Class("CSE 1222", "SP 2017", "Vlad", "A");
        Class class2 = new Class("Math 1151", "SP 2017", "Vlad", "C");
        List<Class> testList = new ArrayList<>();
        testList.add(class1);
        testList.add(class2);
        Semester testSem = new Semester("SP", "2017", testList, "Vlad");

        semesterDao.insertAll(testSem);
        List<Semester> obtainedSemesters = semesterDao.getByUserNow("Vlad");

        assertEquals("Vlad", obtainedSemesters.get(0).username);
        assertEquals("SP", obtainedSemesters.get(0).session);
        assertEquals("2017", obtainedSemesters.get(0).year);


        List<Class> obtainedClasses = semesterDao.getClassesFromSemesterNow("SP 2017", "Vlad");
        assertEquals("CSE", obtainedClasses.get(0).department);
        assertEquals("1222", obtainedClasses.get(0).courseNumber);
        assertEquals("A", obtainedClasses.get(0).grade);
        assertEquals("Math", obtainedClasses.get(1).department);
        assertEquals("1151", obtainedClasses.get(1).courseNumber);
        assertEquals("C", obtainedClasses.get(1).grade);

    }
}