package com.spitslide.derdasdie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class WordActivity extends ThemedActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, new WordFragment())
                .commit();








    }


}
