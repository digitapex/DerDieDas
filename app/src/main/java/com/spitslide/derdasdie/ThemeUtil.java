package com.spitslide.derdasdie;


import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

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

    public static int getPressedButtonTxtColorAttr(Context context) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.buttonTextPressed, typedValue, true);
        int color = typedValue.data;
        return color;
    }

    public static int getNormalButtonTxtColorAttr(Context context) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.buttonTextNormal, typedValue, true);
        int color = typedValue.data;
        return color;
    }

    public static int getAnimation(Context context) {
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.button_animation, typedValue, true);
//        int color = typedValue.data;
        int drawableId = typedValue.resourceId;
        return drawableId;
    }

}
