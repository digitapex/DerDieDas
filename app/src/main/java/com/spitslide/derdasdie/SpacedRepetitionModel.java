package com.spitslide.derdasdie;


import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class SpacedRepetitionModel {

    private static final int REPETITION_FOR_WRONG = 10;
    private static final int REPETITION_FOR_CORRECT = 2;
    private static final int TIMES_TO_ANSWER_TO_REMOVE = 5;

    public float getNewScore(float lastScore, Noun noun, boolean isCorrect){
        float score = 0;
        if (!isCorrect) {
            if (noun.getTimesAnswered() > 0) {
                score = lastScore == 0 ? 0 : lastScore - 0.5f;
            } else {
                score = lastScore;
            }
        } else {
            if (noun.getTimesAnswered() == 0) {
                score = lastScore + 0.5f;
            } else if (noun.getTimesAnswered() >= TIMES_TO_ANSWER_TO_REMOVE - 1) {
                score = lastScore + 0.5f;
            }
        }
        return score;
    }

    public List<Noun> getUpdatedNounList(List<Noun> nounList, Noun noun, boolean isCorrect) {
        if (nounList.size() == 0) {
            return null;
        }
        nounList.remove(0);
        if (!isCorrect) {
            noun.setTimesAnswered(0);
            nounList.add(REPETITION_FOR_WRONG <= nounList.size() ? REPETITION_FOR_WRONG : nounList.size(), noun);

        } else {
            noun.setTimesAnswered(noun.getTimesAnswered() + 1);
            if (noun.getTimesAnswered() < TIMES_TO_ANSWER_TO_REMOVE) {
                nounList.add(REPETITION_FOR_CORRECT <= nounList.size() ? REPETITION_FOR_CORRECT : nounList.size(), noun);
            }
        }
        return nounList;

    }

//    public static void updateScore(Context context, Noun noun, boolean isCorrect) {
//
//        // if wrong answer, reset back to 0 for that word if we have already given 0.5, otherwise we don't substract anything (if wrong answer on the first try)
//        if (!isCorrect) {
//
//            // if correct answer, we give 0.5 only when first answered and additional 0.5 when removing word when 5 times in a row
//        } else {
//
//        }
//    }
}
