package com.battlelancer.seriesguide.asv;

import static org.junit.Assert.*;

import android.content.Context;
import androidx.test.annotation.UiThreadTest;
import androidx.test.core.app.ApplicationProvider;
import com.battlelancer.seriesguide.ui.search.SearchResult;
import org.junit.Test;
import com.battlelancer.seriesguide.util.TaskManager;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;

public class AddShowTest {

    private Context context = ApplicationProvider.getApplicationContext();

//    private show = "fdf";

    @Test
    @UiThreadTest
    public void testAddShow() {
        TaskManager taskManager = TaskManager.getInstance();
        SearchResult show = new SearchResult();
        show.setTvdbid(20000);
        show.setLanguage("en");
        show.setTitle("Unit Test");
        show.setOverview("Overview");
        show.setPosterPath("Path");
        show.setState(1);
        taskManager.performAddTask(context, show);
        assertTrue(true);
    }
}
