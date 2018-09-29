package com.spitslide.derdasdie.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.spitslide.derdasdie.R;
import com.spitslide.derdasdie.utils.DatabaseUtil;
import com.spitslide.derdasdie.utils.FileUtils;

import java.io.UnsupportedEncodingException;

public class StatsActivity extends ThemedActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_simple);
        try {
            setWordStats();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    private void setWordStats() throws UnsupportedEncodingException {
        long allNouns = FileUtils.getNounsCount(this);
        long remainingNouns = new DatabaseUtil(this).getNounsCount();
        long learnedWords = allNouns - remainingNouns;
        ((TextView)findViewById(R.id.word_stats)).setText(learnedWords + " / " + allNouns);
    }


    private void showDialog(int title, int text) {
        String titleString = getResources().getString(title);
        String textString = getResources().getString(text);
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle(titleString);
        builder.setMessage(textString);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onFullWords(View view) {
        showDialog(R.string.full_words_title, R.string.full_words_text);
    }


}
