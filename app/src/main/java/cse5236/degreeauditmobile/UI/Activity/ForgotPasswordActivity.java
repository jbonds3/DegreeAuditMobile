package cse5236.degreeauditmobile.UI.Activity;

import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO;
import static androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cse5236.degreeauditmobile.Model.User;
import cse5236.degreeauditmobile.Model.ViewModel.UsersViewModel;
import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.UI.StringUtils;

public class ForgotPasswordActivity extends AppCompatActivity {
    private static final String TAG = "Update_Password";
    private Button updateSubmitBtn;
    private com.google.android.material.textfield.TextInputEditText secretQuestionText;
    private com.google.android.material.textfield.TextInputEditText newPasswordText;
    private com.google.android.material.textfield.TextInputEditText usernameText;
    private String username;
    private UsersViewModel mUsersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");

        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            setContentView(R.layout.fragment_update_password_land);
        } else {
            setContentView(R.layout.activity_forgot_password);
        }


        mUsersViewModel = new ViewModelProvider(this).get(UsersViewModel.class);


        updateSubmitBtn = (Button) findViewById(R.id.updateSubmitBtn);
        updateSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secretQuestionText = findViewById(R.id.secretQuestionText);
                String secretAnswer = secretQuestionText.getText().toString();

                usernameText = findViewById(R.id.usernameText);
                username = usernameText.getText().toString();

                if (mUsersViewModel.contains(username)) {
                    User user = mUsersViewModel.getUserNow(username);

                    String actualAnswer = user.secretQuestion;

                    if (actualAnswer.equals(secretAnswer)) {
                        newPasswordText = findViewById(R.id.newPasswordTextIET);
                        String new_password = newPasswordText.getText().toString();

                        MessageDigest digest = null;
                        try {
                            digest = MessageDigest.getInstance("SHA-256");
                        } catch (NoSuchAlgorithmException e) {
                            e.printStackTrace();
                        }
                        byte[] sha256HashBytes = digest.digest(new_password.getBytes(StandardCharsets.UTF_8));
                        String sha256HashStrNP = StringUtils.bytesToHex(sha256HashBytes);

                        User updatedUser = new User(username, sha256HashStrNP, secretAnswer);
                        mUsersViewModel.update(updatedUser);
                        Intent LoginIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                        startActivity(LoginIntent);
                    } else {
                        int message = R.string.incorrect_secret_toast;
                        Toast.makeText(ForgotPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                } else {
                    int message = R.string.username_not_found_toast;
                    Toast.makeText(ForgotPasswordActivity.this, message, Toast.LENGTH_SHORT).show();
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
