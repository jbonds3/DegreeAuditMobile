package cse5236.degreeauditmobile.UI.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import cse5236.degreeauditmobile.Model.ViewModel.SemestersViewModel;
import cse5236.degreeauditmobile.R;
import cse5236.degreeauditmobile.UI.Fragment.MainMenuFragement;

public class MainMenuActivity extends AppCompatActivity {
    private static final String TAG = "MAIN_MENU_ACTIVITY";

    private TextView mWelcomeTextView;
    private Button mMainMenuBtn;

    private String mUsername;
    private Context LoginActivity;
    public SemestersViewModel mSemestersViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        if (rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270) {
            setContentView(R.layout.fragment_main_menu_land);
        } else {
            setContentView(R.layout.activity_main_menu);
        }

        Log.d(TAG, "onCreate() called");
        Intent myIntent = getIntent();
        if (myIntent.hasExtra("username")) {
            mUsername = myIntent.getStringExtra("username");
        } else {
            mUsername = "User";
        }
        Log.d(TAG,mUsername);
        mWelcomeTextView = findViewById(R.id.welcome_text_view);
        String welcome = "Welcome, ";
        welcome = welcome.concat(mUsername);
        mWelcomeTextView.setText(welcome);

        Bundle bundle = new Bundle();
        bundle.putString("username", mUsername);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, MainMenuFragement.class, bundle)
                    .commit();
        }

        mSemestersViewModel = new ViewModelProvider(this).get(SemestersViewModel.class);

        //main menu popup btn
        mMainMenuBtn = findViewById(R.id.main_menu_btn);
        mMainMenuBtn.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(MainMenuActivity.this, mMainMenuBtn);

            // Inflating popup menu from popup_menu.xml file
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(menuItem -> {

                String checkProgressStr = getString(R.string.check_progress_text);
                String addSemesterStr = getString(R.string.add_semester_button);
                String editAccountInfoStr = getString(R.string.edit_account_info_button);
                String logoutAccountStr = getString(R.string.logout_account_button);
                String deleteStr = getString(R.string.delete_sem_class_button);
                String editSemesterStr = getString(R.string.edit_semester_button);
                String gradMajorEntryStr = getString(R.string.GraduationMajorEntryText);

                if (menuItem.getTitle().equals(checkProgressStr)) {
                    Intent checkProgressIntent = new Intent(MainMenuActivity.this,CheckProgressActivity.class);
                    checkProgressIntent.putExtra("username", mUsername);
                    startActivity(checkProgressIntent);
                } else if (menuItem.getTitle().equals(addSemesterStr)) {
                    Intent addSemesterIntent = new Intent(MainMenuActivity.this,AddSemesterActivity.class);
                    addSemesterIntent.putExtra("username", mUsername);
                    startActivity(addSemesterIntent);
                } else if (menuItem.getTitle().equals(editAccountInfoStr)) {
                    Intent updatePasswordIntent = new Intent(MainMenuActivity.this,UpdatePasswordActivity.class);
                    updatePasswordIntent.putExtra("username", mUsername);
                    startActivity(updatePasswordIntent);
                } else if (menuItem.getTitle().equals(logoutAccountStr)) {
                    Intent logoutAccountIntent = new Intent(MainMenuActivity.this,LoginActivity.class);
                    logoutAccountIntent.putExtra("username", "Logged Out");
                    startActivity(logoutAccountIntent);
                } else if (menuItem.getTitle().equals(deleteStr)) {
                    mSemestersViewModel.deleteAllSemesters();
                    mSemestersViewModel.deleteAllClasses();
                    Toast.makeText(MainMenuActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                } else if (menuItem.getTitle().equals(editSemesterStr)) {
                    Intent editSemesterIntent = new Intent(MainMenuActivity.this,EditSemesterActivity.class);
                    editSemesterIntent.putExtra("username", mUsername);
                    startActivity(editSemesterIntent);
                } else if (menuItem.getTitle().equals(gradMajorEntryStr)) {
                    Intent gradMajorEntryIntent = new Intent(MainMenuActivity.this,GradMajorEntryActivity.class);
                    gradMajorEntryIntent.putExtra("username", mUsername);
                    startActivity(gradMajorEntryIntent);
                } else {
                    // Toast message on menu item clicked
                    Toast.makeText(MainMenuActivity.this, "You Clicked " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();
                }
                return true;
            });

            // Showing the popup menu
            popupMenu.show();
        });

//        Intent i = getIntent();
//        String x ="";
//        if (myIntent.hasExtra("SEMESTER")) {
//            x = i.getStringExtra("SEMESTER");
//        } else {
//            x = "nope";
//        }



//        mSemestersViewModel.getAllClass().observe(this, classes -> {
//            if (classes.size() < 1) {
//                semText.setText("No");
//            } else {
//                semText.setText(String.valueOf(classes.get(0).courseNumber + "\n"));
//            }
//        });


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