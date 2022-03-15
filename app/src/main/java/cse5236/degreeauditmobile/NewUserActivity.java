package cse5236.degreeauditmobile;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import cse5236.degreeauditmobile.databinding.ActivityNewUserBinding;

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

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();;
        userDao = db.userDao();

        CreateUserButton = (Button) findViewById(R.id.createUserButton);
        CreateUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usernameText = findViewById(R.id.usernameTextIET);
                String username = usernameText.getText().toString();

                if (userDao.hasEntry(username)) {
                    int message = R.string.username_already_exists_toast;
                    Toast.makeText(NewUserActivity.this, message, Toast.LENGTH_SHORT).show();
                } else {
                    passwordText = findViewById(R.id.passwordTextIET);
                    String password = passwordText.getText().toString();
                    User toAdd = new User(username, password);
                    userDao.insertAll(toAdd);
                    Intent LoginIntent = new Intent(NewUserActivity.this,LoginActivity.class);
                    startActivity(LoginIntent);
                }
            }
        });
    }
}