package com.battlelancer.seriesguide.asv;

import static junit.framework.TestCase.assertTrue;

import androidx.fragment.app.Fragment;
import androidx.test.rule.ActivityTestRule;
import com.battlelancer.seriesguide.ui.MoviesActivity;
import com.battlelancer.seriesguide.ui.movies.MoviesDiscoverAdapter;
import com.battlelancer.seriesguide.ui.movies.MoviesDiscoverFragment;
import java.util.List;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MovieDiscoverTest {
    @Rule
    public ActivityTestRule<MoviesActivity> rule = new ActivityTestRule<>(MoviesActivity.class);

    private MoviesActivity moviesActivity;
    private List<Fragment> fragments;

    @Before
    public void setUp() {
        moviesActivity = rule.getActivity();
        fragments = moviesActivity.getSupportFragmentManager().getFragments();
    }

    @Test
    public void testListHasItems() {
        for (Fragment fragment: fragments) {
            if (fragment instanceof MoviesDiscoverFragment) {
                MoviesDiscoverAdapter adapter = ((MoviesDiscoverFragment) fragment).adapter;
                int itemCount = adapter.getItemCount();
                assertTrue(itemCount > 0);
            }
        }
    }
}
