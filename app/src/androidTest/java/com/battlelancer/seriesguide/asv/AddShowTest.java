package com.battlelancer.seriesguide.asv;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

import android.content.Context;
import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ApplicationProvider;
import com.battlelancer.seriesguide.model.SgShow;
import com.battlelancer.seriesguide.provider.SeriesGuideDatabase;
import com.battlelancer.seriesguide.provider.SgRoomDatabase;
import com.battlelancer.seriesguide.ui.search.SearchResult;
import com.battlelancer.seriesguide.ui.shows.ShowsAdapter;
import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.battlelancer.seriesguide.util.TaskManager;
import androidx.sqlite.db.SimpleSQLiteQuery;
import org.mockito.Mock;
import com.battlelancer.seriesguide.provider.RoomDatabaseTestHelper;

public class AddShowTest {

    private Context context = ApplicationProvider.getApplicationContext();

    private SgRoomDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, SgRoomDatabase.class)
                .addCallback(SgRoomDatabase.CALLBACK)
                .build();
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }


    @Test
    public void testAddShow() {
        TaskManager taskManager = TaskManager.getInstance();
        SearchResult show = new SearchResult();
        show.setTvdbid(20000);
        show.setLanguage("en");
        show.setTitle("Unit Test");
        show.setOverview("Overview");
        show.setPosterPath("Path");
        show.setState(1);
//        taskManager.performAddTask(context, show);
//        String queryString = "SELECT * FROM series WHERE series_hidden=0 ORDER BY series_favorite DESC,seriestitle COLLATE NOCASE ASC";
//        LiveData<List<SgShow>> queryResult = SgRoomDatabase
//                .getInstance(context).showHelper()
//                .queryShows(new SimpleSQLiteQuery(queryString, null));
        RoomDatabaseTestHelper.insertShow(show);
        SgShow showFromDatabase = db.showHelper().getShow();
        assertEquals(show.getTitle(), showFromDatabase.title);
    }

}
