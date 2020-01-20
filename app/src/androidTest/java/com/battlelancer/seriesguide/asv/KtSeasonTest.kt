package com.battlelancer.seriesguide.asv

import android.os.Bundle
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.battlelancer.seriesguide.R
import com.battlelancer.seriesguide.ui.overview.SeasonsFragment
import com.battlelancer.seriesguide.ui.stats.StatsFragment
import junit.framework.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import java.util.regex.Pattern.matches

class KtSeasonTest {

    val args = Bundle().apply {
        putString(SeasonsFragment.ARG_SHOW_TVDBID, 257655.toString())
    }

    @Test
    fun testEventFragment() {
        val scenario = launchFragmentInContainer<SeasonsFragment>(args)
//        onView(withId(R.id.menu_action_seasons_watched_all))
//            .check(matches())
//        scenario.onFragment {
//            it.buttonWatchedAll
//        }
        assertTrue(true)
    }
}