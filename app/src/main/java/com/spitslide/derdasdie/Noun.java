package com.spitslide.derdasdie;


public class Noun {

    private String noun;
    private String gender;
    private int timesAnswered;

    public Noun(String noun, String gender, int timesAnswered) {
        this.noun = noun;
        this.gender = gender;
        this.timesAnswered = timesAnswered;
    }

    public String getNoun() {
        return noun;
    }

    public void setNoun(String noun) {
        this.noun = noun;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getTimesAnswered() {
        return timesAnswered;
    }

    public void setTimesAnswered(int timesAnswered) {
        this.timesAnswered = timesAnswered;
    }
}
