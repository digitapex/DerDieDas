package com.spitslide.derdiedas.ui;


import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.spitslide.derdiedas.R;
import com.spitslide.derdiedas.utils.PrefsUtil;
import com.spitslide.derdiedas.utils.ThemeUtil;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        final Preference themePreference = getPreferenceScreen().findPreference("theme");
        if (PrefsUtil.getPrefs(getActivity(), "theme")) {
            themePreference.setSummary("Dark theme");
        }
        themePreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object o) {
                ThemeUtil.setThemeChanged(true);
                getActivity().recreate();
                return true;
            }

        });


    }
}
