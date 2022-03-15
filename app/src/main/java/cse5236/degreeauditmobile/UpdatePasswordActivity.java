package cse5236.degreeauditmobile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class UpdatePasswordActivity extends AppCompatActivity {
    private static final String TAG = "Update_Password";
    private Button updateSubmitBtn;
    private Button deleteUserButton;
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

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();;
        userDao = db.userDao();

        updateSubmitBtn = (Button) findViewById(R.id.updateSubmitBtn);
        updateSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPasswordText = findViewById(R.id.oldPasswordTextIET);
                String entered_old_password = oldPasswordText.getText().toString();

                String actualPassword = userDao.getPassword(username);
                if (entered_old_password.equals(actualPassword)) {
                    newPasswordText = findViewById(R.id.newPasswordTextIET);
                    String new_password = newPasswordText.getText().toString();
                    User updatedUser = new User(username, new_password);
                    userDao.UpdatePassword(updatedUser);
                    Intent mainMenuIntent = new Intent(UpdatePasswordActivity.this,MainMenuActivity.class);
                    mainMenuIntent.putExtra("username", username);
                    startActivity(mainMenuIntent);
                } else {
                    int message = R.string.incorrect_password_toast;
                    Toast.makeText(UpdatePasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                }

            }
        });

        deleteUserButton = findViewById(R.id.deleteUserButton);
        deleteUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User toDelete = userDao.findByName(username);
                userDao.delete(toDelete);
                Intent LoginIntent = new Intent(UpdatePasswordActivity.this,LoginActivity.class);
                startActivity(LoginIntent);
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
