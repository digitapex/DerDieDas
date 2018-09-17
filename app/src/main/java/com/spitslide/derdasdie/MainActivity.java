package com.spitslide.derdasdie;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void onButton(View view) {
        String activity = view.getTag().toString();
        if (activity.equals("practice")) {
            startActivity(new Intent(this, WordActivity.class));
        } else if (activity.equals("settings")) {
            startActivity(new Intent(this, SettingsActivity.class));
        }
    }
}
