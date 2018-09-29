package com.spitslide.derdiedas.utils;


import android.content.Context;
import android.preference.PreferenceManager;

public class PrefsUtil {

    public static boolean getPrefs(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(key, false);
    }
}
