package com.spitslide.derdasdie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            System.out.println((result.toString("UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_main);
    }
}
