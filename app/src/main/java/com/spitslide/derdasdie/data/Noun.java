package com.spitslide.derdasdie.data;


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


    public String getGender() {
        return gender;
    }


    public int getTimesAnswered() {
        return timesAnswered;
    }

    public void setTimesAnswered(int timesAnswered) {
        this.timesAnswered = timesAnswered;
    }
}
