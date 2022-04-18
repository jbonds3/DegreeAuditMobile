package cse5236.degreeauditmobile.UI.Activity;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.room.Room;
import cse5236.degreeauditmobile.Model.AppDatabase;
import cse5236.degreeauditmobile.Model.DatabaseSingleton;
import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.Model.User;
import cse5236.degreeauditmobile.Model.UserDao;
import cse5236.degreeauditmobile.UI.StringUtils;

public class UpdatePasswordActivity extends AppCompatActivity {
    private static final String TAG = "Update_Password";
    private Button updateSubmitBtn;
    private Button mSwitchModeButton;
    private com.google.android.material.textfield.TextInputEditText oldPasswordText;
    private com.google.android.material.textfield.TextInputEditText newPasswordText;
    private String username;
    private UserDao userDao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_update_password);

        Intent myIntent = getIntent();
        if (myIntent.hasExtra("username")) {
            username = myIntent.getStringExtra("username");
        } else {
            username = "User";
        }

        db = DatabaseSingleton.getDatabaseInstance("App_Database", getApplicationContext());
        userDao = db.userDao();

        updateSubmitBtn = (Button) findViewById(R.id.updateSubmitBtn);
        updateSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPasswordText = findViewById(R.id.oldPasswordTextIET);
                String entered_old_password = oldPasswordText.getText().toString();

                String actualPassword = userDao.getPassword(username);

                //SHA-256 Password security
                MessageDigest digest = null;
                try {
                    digest = MessageDigest.getInstance("SHA-256");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                byte[] sha256HashBytes = digest.digest(entered_old_password.getBytes(StandardCharsets.UTF_8));
                String sha256HashStrOP = StringUtils.bytesToHex(sha256HashBytes);

                if (actualPassword.equals(sha256HashStrOP)) {
                    newPasswordText = findViewById(R.id.newPasswordTextIET);
                    String new_password = newPasswordText.getText().toString();

                    digest = null;
                    try {
                        digest = MessageDigest.getInstance("SHA-256");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    sha256HashBytes = digest.digest(new_password.getBytes(StandardCharsets.UTF_8));
                    String sha256HashStrNP = StringUtils.bytesToHex(sha256HashBytes);

                    User updatedUser = new User(username, sha256HashStrNP);
                    userDao.UpdateUser(updatedUser);
                    Intent mainMenuIntent = new Intent(UpdatePasswordActivity.this,MainMenuActivity.class);
                    mainMenuIntent.putExtra("username", username);
                    startActivity(mainMenuIntent);
                } else {
                    int message = R.string.incorrect_password_toast;
                    Toast.makeText(UpdatePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }
        });

        mSwitchModeButton = findViewById(R.id.switchModeButton);
        int text;
        if (AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES) {
            text = R.string.DefaultModeButtonText;
        } else {
            text = R.string.DarkModeButtonText;
        }
        mSwitchModeButton.setText(text);
        mSwitchModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentMode = AppCompatDelegate.getDefaultNightMode();
                if (currentMode == MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO);
                    mSwitchModeButton.setText(R.string.DarkModeButtonText);
                } else {
                    AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES);
                    mSwitchModeButton.setText(R.string.DefaultModeButtonText);
                }
            }
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
