package com.battlelancer.seriesguide.asv;

import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.test.core.app.ApplicationProvider;
import com.battlelancer.seriesguide.R;
import java.util.Locale;
import org.junit.Test;

public class LocaleTest {

    private Context context = ApplicationProvider.getApplicationContext();

    private void setLocale(String language, String country) {
        Locale locale = new Locale(language, country);
        Locale.setDefault(locale);

        Resources res = context.getResources();
        Configuration config = res.getConfiguration();
        config.setLocale(locale);
        res.updateConfiguration(config, res.getDisplayMetrics());
    }

    @Test
    public void testFrenchLocale() {
        setLocale("fr", "FR");
        String showsTitle = context.getString(R.string.shows);
        assertEquals("Séries", showsTitle);
    }

    @Test
    public void testJapaneseLocale() {
        setLocale("ja", "JA");
        String showsTitle = context.getString(R.string.shows);
        assertEquals("番組", showsTitle);
    }

    @Test
    public void testPunjabiLocale() {
        setLocale("pa", "PA");
        String showsTitle = context.getString(R.string.shows);
        assertEquals("Shows", showsTitle);
    }
}
