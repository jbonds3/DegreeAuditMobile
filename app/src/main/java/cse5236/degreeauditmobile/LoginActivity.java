package cse5236.degreeauditmobile;

import androidx.appcompat.app.AppCompatActivity;

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
    private com.google.android.material.textfield.TextInputEditText loginText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate() called");
        setContentView(R.layout.activity_login);

        /*
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_login_form, LoginFormFragment.class, null)
                    .commit();
        }*/

        loginSubmitBtn = (Button) findViewById(R.id.loginSubmitBtn);
        Intent mainMenuIntent = new Intent(LoginActivity.this,MainMenuActivity.class);

        loginSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginText = findViewById(R.id.signInTextIET);
                String username = loginText.getText().toString();
                mainMenuIntent.putExtra("username",username);
                startActivity(mainMenuIntent);
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