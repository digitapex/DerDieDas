package com.spitslide.derdasdie;


import java.util.ArrayList;
import java.util.Random;

public class SpacedRepetitionModel {

    private static final int REPETITION_FOR_WRONG = 10;
    private static final int REPETITION_FOR_CORRECT = 20;
    private static final float PROBABILITY_WHEN_CORRECT = 0.6f;
    private static ArrayList<String> updatingList;

    private static void reinsertWord(ArrayList<String> arrayList, boolean isCorrect) {
        updatingList = arrayList;
        if (updatingList.size() == 0) {
            return;
        }
        String currentElement = updatingList.get(0);
        updatingList.remove(0);

        if (!isCorrect) {
            updatingList.add(REPETITION_FOR_WRONG <= updatingList.size() ? REPETITION_FOR_WRONG : updatingList.size(), currentElement);
        } else {
            float random = new Random().nextFloat();
            if (random <= PROBABILITY_WHEN_CORRECT) {
                updatingList.add(REPETITION_FOR_CORRECT <= updatingList.size() ? REPETITION_FOR_CORRECT : updatingList.size(), currentElement);
            }
        }
    }

    static ArrayList<String> getUpdatedList(){
        return updatingList;
    }
}
