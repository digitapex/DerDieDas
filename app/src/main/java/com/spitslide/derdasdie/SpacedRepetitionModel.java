package com.spitslide.derdasdie;


import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SpacedRepetitionModel {

    private static final int REPETITION_FOR_WRONG = 2;
    private static final int REPETITION_FOR_CORRECT = 5;
    private static final int TIMES_TO_ANSWER_TO_REMOVE = 5;

    public static void updateGlobalNounList(Context context, List<Noun> nounList, Noun noun, boolean isCorrect) {
        if (nounList.size() == 0) {
            return;
        }
        nounList.remove(0);
        if (!isCorrect) {
            noun.setTimesAnswered(0);
            nounList.add(REPETITION_FOR_WRONG <= nounList.size() ? REPETITION_FOR_WRONG : nounList.size(), noun);
        } else {
            noun.setTimesAnswered(noun.getTimesAnswered() + 1);
            if(noun.getTimesAnswered() < TIMES_TO_ANSWER_TO_REMOVE) {
                nounList.add(REPETITION_FOR_CORRECT <= nounList.size() ? REPETITION_FOR_CORRECT : nounList.size(), noun);
            }
        }
        ((WordActivity)context).updateNounList(nounList);
    }

    public static void updateScore(Context context, boolean isCorrect) {
        DatabaseUtil databaseUtil = new DatabaseUtil(context);
        int lastScore = databaseUtil.getLastScore();
        if (!isCorrect) {
            databaseUtil.addScore(lastScore == 0 ? 0 : lastScore - 1);
        } else {
            databaseUtil.addScore(lastScore + 1);
        }
    }
}
