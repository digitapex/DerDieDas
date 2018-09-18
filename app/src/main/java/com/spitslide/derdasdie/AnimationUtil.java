package com.spitslide.derdasdie;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.widget.Button;

public class AnimationUtil {

    static void animateButtonDrawable(Context context, Button button) {
        button.setBackgroundResource(ThemeUtil.getAnimation(context));
        AnimationDrawable buttonAnimation = (AnimationDrawable) button.getBackground();
        buttonAnimation.start();
    }


    static void animateTextColor(Button button, Context context) {
        // this has to match the duration in button_animation_correct_dark.xmlk.xml
        final int halfDuration = 200;
        int duration = 2 * halfDuration;
        final ObjectAnimator colorAnim = ObjectAnimator.ofInt(button, "textColor",
                ThemeUtil.getNormalButtonTxtColorAttr(context), ThemeUtil.getPressedButtonTxtColorAttr(context));
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
        // this number has to be one less than the number of full cycles in button_animation_correct_dark_dark.xml
        colorAnim.setRepeatCount(3);
        colorAnim.setDuration(duration);
        colorAnim.start();
    }
}