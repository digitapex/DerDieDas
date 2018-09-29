package com.spitslide.derdiedas.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.spitslide.derdiedas.data.Noun;
import com.spitslide.derdiedas.R;
import com.spitslide.derdiedas.utils.DatabaseUtil;

import java.util.List;

public class WordActivity extends ThemedActivity {

    private List<Noun> nounList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        nounList = new DatabaseUtil(this).getAllNouns();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.frame, new WordFragment())
                .commit();
    }


    public void replaceFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        fragmentTransaction.replace(R.id.frame, new WordFragment());
        fragmentTransaction.commit();
    }

    public List<Noun> getCurrentNounList() {
        return nounList;
    }

    public void updateNounList(List<Noun> nounList) {
        this.nounList = nounList;
    }

    @Override
    protected void onPause() {
        super.onPause();
        new Thread(){
            @Override
            public void run() {
                new DatabaseUtil(WordActivity.this).addAllNouns(nounList);
            }
        }.start();
    }
}
