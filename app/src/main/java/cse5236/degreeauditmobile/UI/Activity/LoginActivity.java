package cse5236.degreeauditmobile.UI.Activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import cse5236.degreeauditmobile.Model.AppDatabase;
import cse5236.degreeauditmobile.Model.DatabaseSingleton;
import cse5236.degreeauditmobile.Model.User;
import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.Model.UserDao;
import cse5236.degreeauditmobile.UI.StringUtils;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

public class LoginActivity extends AppCompatActivity {
    final long recentTimer = 300000;
    final long timeout = 300000;
    final int maxAttempts = 5;

    private static final String TAG = "THE Login Activity";
    private Button loginSubmitBtn;
    private Button newUserButton;
    private com.google.android.material.textfield.TextInputEditText loginText;
    private com.google.android.material.textfield.TextInputEditText passwordText;
    private AppDatabase db;
    private UserDao userDao;
    private String logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_login);
        Intent myIntent = getIntent();
        if (myIntent.hasExtra("logout")) {
            logout = myIntent.getStringExtra("logout");
        } else {
            logout = "";
        }

        if (!logout.isEmpty()) {
            Toast.makeText(LoginActivity.this, logout, Toast.LENGTH_SHORT).show();
        }
        // LOGIN BTN
        loginSubmitBtn = (Button) findViewById(R.id.loginSubmitBtn);

        // LOGIN BTN onClickListener with lambda exp
        loginSubmitBtn.setOnClickListener(v -> {
                loginText = findViewById(R.id.signInTextIET);
                String username = loginText.getText().toString();

                // if user in Room database
                if (userDao.hasEntry(username)) {
                    User currentUser = userDao.findByName(username);
                    long currentTimeStamp = System.currentTimeMillis();
                    if ((currentUser.recentIncorrectAttempts > maxAttempts) && (currentTimeStamp - currentUser.timeStamp < timeout)) {
                        int message = R.string.timeout_toast;
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    } else {
                        passwordText = findViewById(R.id.passwordTextIET);
                        String enteredPassword = passwordText.getText().toString();

                        //SHA-256 Password security
                        MessageDigest digest = null;
                        try {
                            digest = MessageDigest.getInstance("SHA-256");
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        byte[] sha256HashBytes = digest.digest(enteredPassword.getBytes(StandardCharsets.UTF_8));
                        String sha256HashStr = StringUtils.bytesToHex(sha256HashBytes);

                        String actualPassword = currentUser.password;

                        // check input password match user password
                        if (sha256HashStr.equals(actualPassword)) {
                            currentUser.recentIncorrectAttempts = 0;
                            userDao.UpdateUser(currentUser);
                            Intent mainMenuIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                            mainMenuIntent.putExtra("username", username);
                            startActivity(mainMenuIntent);
                        } else {
                            long lastAttempt = currentUser.timeStamp;
                            currentUser.timeStamp = currentTimeStamp;
                            if (lastAttempt - currentTimeStamp < recentTimer) {
                                currentUser.recentIncorrectAttempts = currentUser.recentIncorrectAttempts + 1;
                            } else {
                                currentUser.recentIncorrectAttempts = 1;
                            }
                            userDao.UpdateUser(currentUser);
                            int message = R.string.incorrect_password_toast;
                            Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                        }
                    }
                    } else{
                        int message = R.string.username_not_found_toast;
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
        });

        db = DatabaseSingleton.getDatabaseInstance("App_Database", getApplicationContext());
        userDao = db.userDao();

        newUserButton = findViewById(R.id.newUserButton);
        newUserButton.setOnClickListener(v -> {
                Intent newUserIntent = new Intent(LoginActivity.this,NewUserActivity.class);
                startActivity(newUserIntent);
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }
    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }
    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
}