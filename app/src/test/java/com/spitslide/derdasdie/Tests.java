package com.spitslide.derdasdie;


import com.spitslide.derdasdie.data.Noun;
import com.spitslide.derdasdie.data.SpacedRepetitionModel;

import org.junit.Test;

public class Tests {
//SpacedRepetitionModel model;
//    @BeforeClass
//    public void setUp() {
////        model = new SpacedRepetitionModel();
//
//    }

    @Test
    public void AnswerTest1() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 0;
        float lastScore = 0;
        boolean correct = true;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0.5f, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest2() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 1;
        float lastScore = 0;
        boolean correct = true;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest3() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 4;
        float lastScore = 0;
        boolean correct = true;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0.5f, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest4() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 2;
        float lastScore = 0;
        boolean correct = true;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest5() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 0;
        float lastScore = 0;
        boolean correct = false;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest5a() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 0;
        float lastScore = 2;
        boolean correct = false;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(2, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest6() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 1;
        float lastScore = 0;
        boolean correct = false;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest7() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 1;
        float lastScore = 0.5f;
        boolean correct = false;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest8() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 3;
        float lastScore = 1;
        boolean correct = false;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0.5f, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest9() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 4;
        float lastScore = 1;
        boolean correct = false;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(0.5f, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

    @Test
    public void AnswerTest10() {
        SpacedRepetitionModel model = new SpacedRepetitionModel();
        int timesAnswered = 2;
        float lastScore = 3;
        boolean correct = false;
        Noun noun = new Noun("Haus", "das", timesAnswered);
        assertEquals(2.5f, model.getNewScore(lastScore, noun, correct), 0.01f);
    }

}
