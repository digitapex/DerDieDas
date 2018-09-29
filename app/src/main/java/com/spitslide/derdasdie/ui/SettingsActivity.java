package com.spitslide.derdasdie.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;

import com.spitslide.derdasdie.R;

public class SettingsActivity extends ThemedActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SettingsFragment())
                .commit();
    }
}
