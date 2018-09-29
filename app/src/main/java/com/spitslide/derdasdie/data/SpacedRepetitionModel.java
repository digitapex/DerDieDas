package com.spitslide.derdasdie.data;


import java.util.List;

public class SpacedRepetitionModel {

    private static final int REPETITION_FOR_WRONG = 10;
    private static final int REPETITION_FOR_CORRECT = 2;
    private static final int TIMES_TO_ANSWER_TO_REMOVE = 3;


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
}
