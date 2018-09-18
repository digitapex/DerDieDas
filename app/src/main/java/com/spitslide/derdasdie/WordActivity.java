package com.spitslide.derdasdie;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class WordActivity extends ThemedActivity {

    private String correctGender;
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
        correctGender = randomNoun.split(",")[1];
        nounView.setText(noun);

    }

    public void onButton(View view) {
        Button pressedButton = (Button) view;
        String pressedButtonGender = getResources().getResourceEntryName(view.getId());
        if (pressedButtonGender.equals(correctGender)) {
            nounView.setText(GenderConvert.toFullGender(correctGender) + " " + nounView.getText());
            pressedButton.setBackgroundResource(R.drawable.button_correct);
//            pressedButton.setTextColor(ThemeUtil.getPressedButtonTxtColorAttr(this));
        } else {
            int idResource = getResources().getIdentifier(correctGender, "id", getPackageName());
            Button correctButton = findViewById(idResource);
            AnimationUtil.animateButtonDrawable(this, correctButton);
            // animating text colod not working on real device, but works in emulator, so better to just animate background
//            AnimationUtil.animateTextColor(correctButton, this);
            pressedButton.setBackgroundResource(R.drawable.button_wrong);
//            pressedButton.setTextColor(ThemeUtil.getPressedButtonTxtColorAttr(this));


        }
    }
}
