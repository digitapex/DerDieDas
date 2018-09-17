package com.spitslide.derdasdie;


import android.content.Context;

public class ThemeUtil {

    private static boolean themeChanged;

    public static boolean isThemeChanged() {
        return themeChanged;
    }

    public static void setThemeChanged(boolean changed) {
        themeChanged = changed;
    }

    public static void changeTheme(Context context){
        if (PrefsUtil.getPrefs(context, "theme")) {
            context.setTheme(R.style.DarkTheme);
        } else {
            context.setTheme(R.style.AppTheme);
        }
    }
}
