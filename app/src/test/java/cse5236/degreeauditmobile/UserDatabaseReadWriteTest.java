package cse5236.degreeauditmobile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.room.Database;
import androidx.test.runner.AndroidJUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import cse5236.degreeauditmobile.Model.AppDatabase;
import cse5236.degreeauditmobile.Model.DatabaseSingleton;
import cse5236.degreeauditmobile.Model.User;
import cse5236.degreeauditmobile.Model.UserDao;
import cse5236.degreeauditmobile.UI.StringUtils;

@RunWith(AndroidJUnit4.class)
public class UserDatabaseReadWriteTest {
    private UserDao userDao;
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = DatabaseSingleton.getDatabaseInstance("Test_Database", context);
        userDao = db.userDao();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void writeUserAndReadInList() throws Exception {
        String password = "123";
        //SHA-256 Password security
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] sha256HashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
        String sha256HashStr = StringUtils.bytesToHex(sha256HashBytes);
        User user = new User("Vlad", sha256HashStr);
        userDao.insertAll(user);
        User byName = userDao.findByName("Vlad");
        assertThat(byName.password, equalTo(user.password));
    }
}