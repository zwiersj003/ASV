package com.battlelancer.seriesguide.asv;
import static junit.framework.TestCase.assertEquals;

import android.widget.TextView;
import androidx.room.testing.MigrationTestHelper;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;
import com.battlelancer.seriesguide.R;
import com.battlelancer.seriesguide.dataliberation.model.Show;
import com.battlelancer.seriesguide.model.SgSeason;
import com.battlelancer.seriesguide.provider.RoomDatabaseTestHelper;
import com.battlelancer.seriesguide.provider.SgRoomDatabase;
import com.battlelancer.seriesguide.ui.stats.StatsActivity;
import com.uwetrottmann.thetvdb.entities.Episode;
import java.io.IOException;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class StatisticsTest {
    @Rule
    public ActivityTestRule<StatsActivity> rule = new ActivityTestRule<>(StatsActivity.class);

    @Rule
    public MigrationTestHelper migrationTestHelper =
            new MigrationTestHelper(InstrumentationRegistry.getInstrumentation(),
                    SgRoomDatabase.class.getCanonicalName(),
                    new FrameworkSQLiteOpenHelperFactory());

    private static final String TEST_DB_NAME = "seriesdatabase-asv";
    private static final Show SHOW = new Show();
    private static final SgSeason SEASON = new SgSeason();
    private static final Episode EPISODE = new Episode();
    private static final Episode EPISODE2 = new Episode();

    private StatsActivity statsActivity;

    @Before
    public void setUp() throws IOException {
        SupportSQLiteDatabase db = migrationTestHelper.createDatabase(TEST_DB_NAME, 45);
        RoomDatabaseTestHelper.insertShow(SHOW, db, 45);
        RoomDatabaseTestHelper.insertSeason(SEASON, db);
        RoomDatabaseTestHelper
                .insertEpisode(EPISODE, SHOW.tvdb_id, SEASON.tvdbId, SEASON.number, db);
        RoomDatabaseTestHelper
                .insertEpisode(EPISODE2, SHOW.tvdb_id, SEASON.tvdbId, SEASON.number, db);
        db.close();
    }

    @Test
    public void testEpisodes() {
        statsActivity = rule.getActivity();
        TextView episodes = statsActivity.findViewById(R.id.textViewStatsEpisodes);
        TextView episodesWatched = statsActivity.findViewById(R.id.textViewStatsEpisodesWatched);

        assertEquals("2", episodes.getText());
        assertEquals("0 WATCHED", episodesWatched.getText());
    }

    @Test
    public void testShows() {
        statsActivity = rule.getActivity();
        TextView shows = statsActivity.findViewById(R.id.textViewStatsShows);

        assertEquals("1", shows.getText());
    }

    @Test
    public void testMovies() {
        statsActivity = rule.getActivity();
        TextView movies = statsActivity.findViewById(R.id.textViewStatsMovies);

        assertEquals("0", movies.getText());
    }

    @After
    public void tearDown() {
        // close the database to minimize issues when deleting it in setUp()
//        ApplicationProvider.getApplicationContext().deleteDatabase(TEST_DB_NAME);
    }

    static {
        SHOW.tvdb_id = 21;
        SHOW.title = "Game of thrones";
        SHOW.runtime = 45;
        SHOW.poster = "example.jpg";

        SEASON.tvdbId = 21;
        SEASON.showTvdbId = "21";
        SEASON.number = 2;

        EPISODE.id = 21;
        EPISODE.episodeName = "Episode 1";
        EPISODE.airedEpisodeNumber = 1;

        EPISODE2.id = 22;
        EPISODE2.episodeName = "Episode 2";
        EPISODE2.airedEpisodeNumber = 2;
    }
}
