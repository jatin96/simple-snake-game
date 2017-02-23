package com.difusal.snake;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ActivitySwipeDetector implements View.OnTouchListener {
    static final String logTag = "ActivitySwipeDetector";
    private SwipeInterface activity;
    static final int MIN_SWIPE_DISTANCE = 100;
    static final int MAX_CLICK_TOLERANCE = 50;
    private float downX;
    private float downY;

    public ActivitySwipeDetector(SwipeInterface activity) {
        this.activity = activity;
    }

    public void onRightToLeftSwipe(View v) {
        Log.v(logTag, "RightToLeftSwipe!");
        //activity.right2left(v);
    }

    public void onLeftToRightSwipe(View v) {
        Log.v(logTag, "LeftToRightSwipe!");
        //activity.left2right(v);
    }

    public void onTopToBottomSwipe(View v) {
        Log.v(logTag, "onTopToBottomSwipe!");
       // activity.top2bottom(v);
    }

    public void onBottomToTopSwipe(View v) {
        Log.v(logTag, "onBottomToTopSwipe!");
        //activity.bottom2top(v);
    }

    private void onClick(View v, int x, int y) {
        Log.v(logTag, "onClick!");
        activity.onClick(v, x, y);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                return true;
            case MotionEvent.ACTION_UP: {
                float upX = event.getX();
                float upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // click?
                if (deltaX <= MAX_CLICK_TOLERANCE && deltaY <= MAX_CLICK_TOLERANCE) {
                    this.onClick(v, (int) upX, (int) upY);
                    return true;
                }

                // swipe horizontal?
                if (Math.abs(deltaX) > MIN_SWIPE_DISTANCE) {
                    // left or right
                    if (deltaX < 0) {
                        this.onLeftToRightSwipe(v);
                        return true;
                    }
                    if (deltaX > 0) {
                        this.onRightToLeftSwipe(v);
                        return true;
                    }
                } else {
                    Log.i(logTag, "Horizontal swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_SWIPE_DISTANCE);
                }

                // swipe vertical?
                if (Math.abs(deltaY) > MIN_SWIPE_DISTANCE) {
                    // top or down
                    if (deltaY < 0) {
                        this.onTopToBottomSwipe(v);
                        return true;
                    }
                    if (deltaY > 0) {
                        this.onBottomToTopSwipe(v);
                        return true;
                    }
                } else {
                    Log.i(logTag, "Vertical swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_SWIPE_DISTANCE);
                }
            }
        }

        return false;
    }
}
