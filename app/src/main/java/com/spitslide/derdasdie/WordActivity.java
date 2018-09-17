package com.spitslide.derdasdie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class WordActivity extends ThemedActivity {

    private String gender;
    private TextView nounView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        nounView = findViewById(R.id.nounView);
        String listNouns = null;
        try {
            listNouns = FileUtils.getNounList(this);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] nouns = FileUtils.getLines(listNouns);
        String randomNoun = nouns[new Random().nextInt(100)];
        String noun = randomNoun.split(",")[0];
        gender = randomNoun.split(",")[1];
        nounView.setText(noun);

    }

    public void onButton(View view) {
        String buttonGender = view.getTag().toString();
        if (buttonGender.equals(gender)) {
            nounView.setText(GenderConvert.toFullGender(gender) + " " + nounView.getText());
        }
    }
}
