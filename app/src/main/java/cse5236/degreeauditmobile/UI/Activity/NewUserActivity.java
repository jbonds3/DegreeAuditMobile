package cse5236.degreeauditmobile.UI.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.room.Room;

import cse5236.degreeauditmobile.Model.AppDatabase;
import cse5236.degreeauditmobile.Model.DatabaseSingleton;
import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.Model.User;
import cse5236.degreeauditmobile.Model.UserDao;
import cse5236.degreeauditmobile.UI.StringUtils;

public class NewUserActivity extends AppCompatActivity {
    private static final String TAG = "Create_New_User";
    private Button CreateUserButton;
    private com.google.android.material.textfield.TextInputEditText usernameText;
    private com.google.android.material.textfield.TextInputEditText passwordText;
    private AppDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_new_user);

        db = DatabaseSingleton.getDatabaseInstance("App_Database", getApplicationContext());
        userDao = db.userDao();

        // CREATE USER BTN
        CreateUserButton = (Button) findViewById(R.id.createUserButton);

        // CREATE USER BTN onClickListener with lambda exp
        CreateUserButton.setOnClickListener(v -> {
                usernameText = findViewById(R.id.usernameTextIET);
                String username = usernameText.getText().toString();

                // check if username is in Room database
                if (userDao.hasEntry(username)) {
                    int message = R.string.username_already_exists_toast;
                    Toast.makeText(NewUserActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    passwordText = findViewById(R.id.passwordTextIET);
                    String password = passwordText.getText().toString();

                    //SHA-256 Password security
                    MessageDigest digest = null;
                    try {
                        digest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    byte[] sha256HashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
                    String sha256HashStr = StringUtils.bytesToHex(sha256HashBytes);

                    User toAdd = new User(username, sha256HashStr);
                    userDao.insertAll(toAdd);

                    // Start intent to main menu
                    Intent mainMenuIntent = new Intent(NewUserActivity.this,MainMenuActivity.class);
                    mainMenuIntent.putExtra("username", username);
                    startActivity(mainMenuIntent);
                }
        });
    }
}