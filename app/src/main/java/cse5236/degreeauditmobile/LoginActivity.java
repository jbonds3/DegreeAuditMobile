package cse5236.degreeauditmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "THE Login Activity";
    private Button loginSubmitBtn;
    private Button newUserButton;
    private com.google.android.material.textfield.TextInputEditText loginText;
    private com.google.android.material.textfield.TextInputEditText passwordText;
    private AppDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_login);

        // LOGIN BTN
        loginSubmitBtn = (Button) findViewById(R.id.loginSubmitBtn);

        // LOGIN BTN onClickListener with lambda exp
        loginSubmitBtn.setOnClickListener(v -> {
                loginText = findViewById(R.id.signInTextIET);
                String username = loginText.getText().toString();

                // if user in Room database
                if (userDao.hasEntry(username)) {
                    passwordText = findViewById(R.id.passwordTextIET);
                    String enteredPassword = passwordText.getText().toString();
                    String actualPassword = userDao.getPassword(username);

                    // check input password match user password
                    if (enteredPassword.equals(actualPassword)) {
                        Intent mainMenuIntent = new Intent(LoginActivity.this,MainMenuActivity.class);
                        mainMenuIntent.putExtra("username", username);
                        startActivity(mainMenuIntent);
                    } else {
                        int message = R.string.incorrect_password_toast;
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    int message = R.string.username_not_found_toast;
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
        });

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();;
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