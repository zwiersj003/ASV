package com.battlelancer.seriesguide.asv;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import androidx.test.runner.lifecycle.Stage;
import com.battlelancer.seriesguide.R;
import com.battlelancer.seriesguide.ui.SearchActivity;
import com.battlelancer.seriesguide.ui.ShowsActivity;
import com.battlelancer.seriesguide.util.Shadows;
import java.util.Collection;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class AddShowButtonTest {
    @Rule
    public ActivityTestRule<ShowsActivity> mActivityRule =
            new ActivityTestRule<>(ShowsActivity.class);

    @Test
    public void shouldStartNextActivityWhenButtonIsClicked() {
        Espresso.onView(ViewMatchers.withId(R.id.buttonShowsAdd)).perform(click());
        assertEquals(getActivityInstance().getClass(), SearchActivity.class);
    }

    private Activity getActivityInstance() {
        final Activity[] currentActivity = {null};
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            public void run() {
                Collection resumedActivities = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(
                        Stage.RESUMED);
                if (resumedActivities.iterator().hasNext()) {
                    currentActivity[0] = (Activity) resumedActivities.iterator().next();
                }
            }
        });

        return currentActivity[0];
    }
}
