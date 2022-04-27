package cse5236.degreeauditmobile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.Visibility.GONE;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.widget.Button;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import cse5236.degreeauditmobile.UI.Activity.CheckProgressActivity;
import cse5236.degreeauditmobile.UI.Activity.LoginActivity;
import cse5236.degreeauditmobile.UI.Activity.NewUserActivity;
import cse5236.degreeauditmobile.UI.Activity.UpdatePasswordActivity;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class UI_Test {

    @Test
    public void switchModeTest() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("cse5236.degreeauditmobile", appContext.getPackageName());
        Intent checkProgressIntent = new Intent(appContext, CheckProgressActivity.class);
        ActivityScenario<CheckProgressActivity> update_password = ActivityScenario.launch(checkProgressIntent);
        onView(withId(R.id.requirementsTable)).check(matches(withEffectiveVisibility(GONE)));

    }
}