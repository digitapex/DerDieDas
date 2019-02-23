package com.spitslide.derdiedas.ui;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.View;

import com.spitslide.derdiedas.data.Noun;
import com.spitslide.derdiedas.R;
import com.spitslide.derdiedas.utils.DatabaseUtil;
import com.spitslide.derdiedas.utils.FileUtils;
import com.spitslide.derdiedas.utils.ThemeUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ThemedActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        createDatabaseIfFirstRun();
    }

    public void onButton(View view) {
        String activity = view.getTag().toString();
        switch (activity) {
            case "practice":
                startActivity(new Intent(this, WordActivity.class));
                break;
            case "settings":
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case "stats":
                startActivity(new Intent(this, StatsActivity.class));
                break;
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ThemeUtil.isThemeChanged()) {
            ThemeUtil.changeTheme(this);
            ThemeUtil.setThemeChanged(false);
            this.recreate();
        }
    }


    private void createDatabaseIfFirstRun() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.getBoolean("firstrun", true)) {
            String listNouns = null;
            try {
                listNouns = FileUtils.getNounList(this);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String[] nouns = FileUtils.getLines(listNouns);

            final List<Noun> nounList = new ArrayList<>();
            for (String line : nouns) {
                String noun = line.split(",")[0];
                String gender = line.split(",")[1];
                Noun nounObject = new Noun(noun, gender, 0);
                nounList.add(nounObject);
            }

            new Thread(){
                @Override
                public void run() {
                    new DatabaseUtil(MainActivity.this).addAllNouns(nounList);
                }
            }.start();

            prefs.edit().putBoolean("firstrun", false).apply();
        }
    }

}
