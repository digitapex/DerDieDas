package com.spitslide.derdasdie;


public class Noun {

    private String noun;
    private String gender;

    public Noun(String noun, String gender) {
        this.noun = noun;
        this.gender = gender;
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

}
