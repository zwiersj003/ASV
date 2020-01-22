package com.battlelancer.seriesguide.asv;

import static junit.framework.TestCase.assertTrue;

import androidx.fragment.app.Fragment;
import androidx.room.testing.MigrationTestHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.battlelancer.seriesguide.dataliberation.model.Movie;
import com.battlelancer.seriesguide.provider.RoomDatabaseTestHelper;
import com.battlelancer.seriesguide.provider.SgRoomDatabase;
import com.battlelancer.seriesguide.ui.MoviesActivity;
import com.battlelancer.seriesguide.R;
import com.battlelancer.seriesguide.ui.movies.MoviesDiscoverAdapter;
import com.battlelancer.seriesguide.ui.movies.MoviesDiscoverFragment;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class MovieDiscoverTest {
    @Rule
    public ActivityTestRule<MoviesActivity> rule = new ActivityTestRule<>(MoviesActivity.class);

    private static final String TEST_DB_NAME = "seriesdatabase-asv";
    private MoviesActivity moviesActivity;
    private List<Fragment> fragments;

    @Before
    public void setUp() throws IOException {
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
