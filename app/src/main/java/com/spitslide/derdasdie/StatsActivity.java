package com.spitslide.derdasdie;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.View;

public class StatsActivity extends ThemedActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_simple);
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

    public void onHalfWords(View view) {
        showDialog(R.string.half_words_title, R.string.half_words_text);
    }

    public void onFullWords(View view) {
        showDialog(R.string.full_words_title, R.string.full_words_text);
    }


}
