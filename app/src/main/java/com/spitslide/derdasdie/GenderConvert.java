package com.spitslide.derdasdie;

public class GenderConvert {

    static String toFullGender(String shortGender){
        String fullGender = "";
        switch (shortGender) {
            case "m":
                fullGender = "der";
                break;
            case "n":
                fullGender = "das";
                break;
            case "f":
                fullGender = "die";
                break;
        }
        return fullGender;
    }
}
