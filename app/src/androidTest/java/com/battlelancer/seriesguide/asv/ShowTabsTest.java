package com.battlelancer.seriesguide.asv;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.rule.ActivityTestRule;
import com.battlelancer.seriesguide.provider.SeriesGuideContract;
import com.battlelancer.seriesguide.provider.SeriesGuideDatabase;
import com.battlelancer.seriesguide.ui.ShowsActivity;
import com.battlelancer.seriesguide.ui.shows.CalendarFragment2;
import com.battlelancer.seriesguide.ui.shows.ShowsFragment;
import com.battlelancer.seriesguide.ui.shows.ShowsNowFragment;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class ShowTabsTest {
    @Rule
    public ActivityTestRule<ShowsActivity> mActivityTestRule = new ActivityTestRule<>(
            ShowsActivity.class);

    private ShowsActivity showsActivity;

    @Before
    public void setUp() {
        // delete the database and close the database helper inside the provider
        // to ensure a clean state for the add show test
        Context context = ApplicationProvider.getApplicationContext();
        context.deleteDatabase(SeriesGuideDatabase.DATABASE_NAME);
        context.getContentResolver().query(SeriesGuideContract.Shows.CONTENT_URI_CLOSE,
                null, null, null, null);
        showsActivity = mActivityTestRule.getActivity();
    }

    /**
     * Test if amount of tabs is equal to 4
     */
    @Test
    public void testAmountOfTabs() {
        assertEquals(4, showsActivity.tabsAdapter.getCount());
    }

    /**
     * Test if tab has the right fragment assigned
     */
    @Test
    public void testFragmentClassOfTab() {
        assertTrue(showsActivity.tabsAdapter.getItem(0) instanceof ShowsFragment);
        assertTrue(showsActivity.tabsAdapter.getItem(1) instanceof ShowsNowFragment);
        assertTrue(showsActivity.tabsAdapter.getItem(2) instanceof CalendarFragment2);
        assertTrue(showsActivity.tabsAdapter.getItem(3) instanceof CalendarFragment2);
    }
}
