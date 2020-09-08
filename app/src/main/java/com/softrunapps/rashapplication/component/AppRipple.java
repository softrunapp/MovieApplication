package com.softrunapps.rashapplication.component;

import android.animation.ObjectAnimator;
import android.view.MotionEvent;
import android.view.View;

public class AppRipple implements View.OnTouchListener {
    private static AppRipple instance;

    private AppRipple() {
    }

    public static void setViewRipple(View... view) {
        if (instance == null) {
            instance = new AppRipple();
        }
        instance.setViewRippleAnimation(view);
    }

    private void setViewRippleAnimation(View... view) {
        for (View item : view) {
            item.setOnTouchListener(this);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ObjectAnimator viewObjectAnimator1 = ObjectAnimator.ofFloat(view, "alpha", view.getAlpha(), 1).setDuration(300);
        ObjectAnimator viewObjectAnimator0 = ObjectAnimator.ofFloat(view, "alpha", view.getAlpha(), 0.4f).setDuration(100);
        if (motionEvent.getAction() != MotionEvent.ACTION_DOWN) {
            viewObjectAnimator0.cancel();
            viewObjectAnimator1.start();
        } else {
            viewObjectAnimator1.cancel();
            viewObjectAnimator0.start();
        }
        return false;
    }
}
