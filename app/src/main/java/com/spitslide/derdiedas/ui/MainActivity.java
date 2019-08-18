package com.spitslide.derdiedas.ui;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
            case "share":
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.recommend_app));
                shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.recommend_text));
                startActivity(Intent.createChooser(shareIntent, getString(R.string.recommend_title)));
                break;
            case "rate":
                Uri uriMarket = Uri.parse(getString(R.string.rate_app_market));
                Uri uriWeb = Uri.parse(getString(R.string.rate_app_web));
                Intent rateIntentMarket = new Intent(Intent.ACTION_VIEW, uriMarket);
                Intent rateIntentWeb = new Intent(Intent.ACTION_VIEW, uriWeb);
                try {
                    startActivity(rateIntentMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(rateIntentWeb);
                }
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
