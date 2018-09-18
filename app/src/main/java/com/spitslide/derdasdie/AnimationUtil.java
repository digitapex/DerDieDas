package com.spitslide.derdasdie;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.widget.Button;

public class AnimationUtil {

    static void animateButtonDrawable(Context context, Button button) {
        button.setBackgroundResource(R.drawable.button_animation_correct);
        AnimationDrawable buttonAnimation = (AnimationDrawable) button.getBackground();
        buttonAnimation.start();
    }


    static void animateTextColor(Button button) {
        final int halfDuration = 500;
        int duration = 2 * halfDuration;
        final ObjectAnimator colorAnim = ObjectAnimator.ofInt(button, "textColor",
                Color.WHITE, Color.BLACK);
        colorAnim.setEvaluator(new ArgbEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                int myFrac = 0;
                if (colorAnim.getCurrentPlayTime() > halfDuration) {
                    myFrac = 1;
                }
                return super.evaluate(myFrac, startValue, endValue);
            }
        });
        colorAnim.setRepeatCount(2);
        colorAnim.setDuration(duration);
        colorAnim.start();
    }
}
