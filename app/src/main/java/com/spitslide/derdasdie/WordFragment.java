package com.spitslide.derdasdie;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.util.Random;

public class WordFragment extends Fragment implements View.OnClickListener {

    private String correctGender;
    TextView nounView;
    private View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_word, container, false);
        nounView = v.findViewById(R.id.nounView);
        v.findViewById(R.id.m).setOnClickListener(this);
        v.findViewById(R.id.n).setOnClickListener(this);
        v.findViewById(R.id.f).setOnClickListener(this);
        return v;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String listNouns = null;
        try {
            listNouns = FileUtils.getNounList(getActivity());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String[] nouns = FileUtils.getLines(listNouns);
        String randomNoun = nouns[new Random().nextInt(100)];
        String noun = randomNoun.split(",")[0];
        correctGender = randomNoun.split(",")[1];
        nounView.setText(noun);

    }

    @Override
    public void onClick(View view) {
        Button pressedButton = (Button) view;
        String pressedButtonGender = getResources().getResourceEntryName(view.getId());
        if (pressedButtonGender.equals(correctGender)) {
//            nounView.setText(GenderConvert.toFullGender(correctGender) + " " + nounView.getText());
            pressedButton.setBackgroundResource(R.drawable.button_correct);
//            pressedButton.setTextColor(ThemeUtil.getPressedButtonTxtColorAttr(this));
            AnimationUtil.animateJumpAndSlide(getActivity(), nounView, true);
        } else {
            int idResource = getResources().getIdentifier(correctGender, "id", getActivity().getPackageName());
            Button correctButton = v.findViewById(idResource);
            AnimationUtil.animateButtonDrawable(getActivity(), correctButton);
            // animating text color not working on real device, but works in emulator, so better to just animate background
//            AnimationUtil.animateTextColor(correctButton, this);
            pressedButton.setBackgroundResource(R.drawable.button_wrong);
            AnimationUtil.animateJumpAndSlide(getActivity(), nounView, false);
//            pressedButton.setTextColor(ThemeUtil.getPressedButtonTxtColorAttr(this));


        }
    }
}
