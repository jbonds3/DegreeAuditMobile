package cse5236.degreeauditmobile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenuActivity extends AppCompatActivity {
    private static final String TAG = "MENU_ACTIVITY";

    private TextView mWelcomeTextView;
    private Button mAddSemesterButton;
    private Button mEditSemesterButton;
    private Button mEditAccountInfoButton;
    private Button mProgressButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mWelcomeTextView = findViewById(R.id.welcome_text_view);
        int welcome = R.string.welcome;
        mWelcomeTextView.setText(welcome);

        mProgressButton = findViewById(R.id.progress_button);
        mProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int message = R.string.progress_button_toast;
                Toast.makeText(MainMenuActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });

        mAddSemesterButton = findViewById(R.id.add_semester_button);
        mAddSemesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int message = R.string.add_semester_toast;
                Toast.makeText(MainMenuActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });

        mEditSemesterButton = findViewById(R.id.edit_semester_button);
        mEditSemesterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int message = R.string.edit_semester_toast;
                Toast.makeText(MainMenuActivity.this,message,Toast.LENGTH_SHORT).show();
            }
        });

        mEditAccountInfoButton = findViewById(R.id.edit_account_info_button);
        mEditAccountInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int message = R.string.edit_account_info_toast;
                Toast.makeText(MainMenuActivity.this,message,Toast.LENGTH_SHORT).show();
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