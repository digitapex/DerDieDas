package com.spitslide.derdasdie.ui;


import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.spitslide.derdasdie.R;
import com.spitslide.derdasdie.utils.ThemeUtil;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        final Preference fontPreference = getPreferenceScreen().findPreference("theme");
        fontPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            public boolean onPreferenceChange(Preference preference, Object o) {
                ThemeUtil.setThemeChanged(true);
                getActivity().recreate();
                return true;
            }

        });


    }
}
