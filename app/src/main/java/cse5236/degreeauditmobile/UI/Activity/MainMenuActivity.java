package cse5236.degreeauditmobile.UI.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import cse5236.degreeauditmobile.R;

public class MainMenuActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_MENU_ACTIVITY";

    private TextView mWelcomeTextView;
//    private Button mAddSemesterButton;
//    private Button mEditSemesterButton;
//    private Button mEditAccountInfoButton;
//    private Button mProgressButton;
    private Button mMainMenuBtn;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        Log.d(TAG, "onCreate() called");
        Intent myIntent = getIntent();
        if (myIntent.hasExtra("username")) {
            username = myIntent.getStringExtra("username");
        } else {
            username = "User";
        }
        Log.d(TAG,username);
        mWelcomeTextView = findViewById(R.id.welcome_text_view);
        String welcome = "Welcome, ";
        welcome = welcome.concat(username);
        mWelcomeTextView.setText(welcome);

//        mEditAccountInfoButton = findViewById(R.id.edit_account_info_button);
//        mEditAccountInfoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent updatePasswordIntent = new Intent(MainMenuActivity.this,UpdatePasswordActivity.class);
//                updatePasswordIntent.putExtra("username", username);
//                startActivity(updatePasswordIntent);
//            }
//        });

        //main menu popup btn
        mMainMenuBtn = findViewById(R.id.main_menu_btn);
        mMainMenuBtn.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainMenuActivity.this, mMainMenuBtn);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {

                String checkProgressStr = getString(R.string.check_progress_text);
                String addSemesterStr = getString(R.string.add_semester_button);

                if (menuItem.getTitle() == checkProgressStr) {
                    Intent checkProgressIntent = new Intent(MainMenuActivity.this,CheckProgressActivity.class);
                    startActivity(checkProgressIntent);
                } else if (menuItem.getTitle() == addSemesterStr) {
                    Intent checkProgressIntent = new Intent(MainMenuActivity.this,AddSemesterActivity.class);
                    startActivity(checkProgressIntent);
                } else {
                    // Toast message on menu item clicked
                    Toast.makeText(MainMenuActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            // Showing the popup menu
            popupMenu.show();
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