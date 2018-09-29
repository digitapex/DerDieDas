package com.spitslide.derdasdie.utils;


import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.spitslide.derdasdie.R;
import com.spitslide.derdasdie.ui.WordActivity;

public class AnimationUtil {

    public static void animateButtonDrawable(Context context, Button button) {
        button.setBackgroundResource(ThemeUtil.getAnimation(context));
        AnimationDrawable buttonAnimation = (AnimationDrawable) button.getBackground();
        buttonAnimation.start();
    }

    public static void animateJumpAndSlide(final Context context, final View nounView, boolean isCorrectAnswer) {
        Animation jumpAnim = AnimationUtils.loadAnimation(context, R.anim.jump_animation);
        final Animation waitAnimation = AnimationUtils.loadAnimation(context, R.anim.wait_animation);
        waitAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                ((WordActivity)context).replaceFragment();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });


        jumpAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                ((WordActivity)context).replaceFragment();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        // if correct answer, we do jump and after that slide the new fragment,
        // if wrong, we do flash and after that slide the fragment
        if (isCorrectAnswer) {
            nounView.startAnimation(jumpAnim);
        } else {
            // we are using this dummy animation which does nothing, to wait for the flashing to end
            // and then we replace the fragment (because AnimationDrawable doesn't have a simple
            // onFinished listener) - this means the duration in this animation has to be the sum of
            // durations in button_animation_correct
            nounView.startAnimation(waitAnimation);

        }

    }

}
