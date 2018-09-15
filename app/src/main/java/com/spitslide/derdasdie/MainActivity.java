package com.spitslide.derdasdie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String listNouns = null;
        try {
            listNouns = FileUtils.getNounList(this);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] nouns = FileUtils.getLines(listNouns);
        setContentView(R.layout.activity_main);
    }
}
