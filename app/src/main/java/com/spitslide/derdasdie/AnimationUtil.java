package com.spitslide.derdasdie;


import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class AnimationUtil {

    static void animateButtonDrawable(Context context, Button button) {
        button.setBackgroundResource(ThemeUtil.getAnimation(context));
        AnimationDrawable buttonAnimation = (AnimationDrawable) button.getBackground();
        buttonAnimation.start();
    }


    static void animateTextColor(Button button, Context context) {
        // this has to match the duration in button_animation_correct_dark.xml
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
        // this number has to be one less than the number of full cycles in button_animation_correct_dark.xml
        colorAnim.setRepeatCount(3);
        colorAnim.setDuration(duration);
        colorAnim.start();
    }

    static void animateJumpAndSlide(final Context context, final View nounView, boolean isCorrectAnswer) {
        Animation jumpAnim = AnimationUtils.loadAnimation(context, R.anim.jump_and_slide);
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
        // if wrong, then we just slide after button flashing
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
