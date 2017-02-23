package com.difusal.logic;

import android.graphics.Point;
import android.util.Log;

public class Clock extends SpecialElement {
    private static final String TAG = GamePanel.class.getSimpleName();

    // maximum duration to display clock in snake moves
    private static final int MAX_DURATION = 20;

    // effect duration in seconds
    private static final int EFFECT_DURATION = 10;

    public Clock(Point fieldDimensions, Snake snake, int radius) {
        super(fieldDimensions, snake, radius, MAX_DURATION);

        type = GameElementType.CLOCK;

        Log.v(TAG, "Clock created");
    }

    public static int getEffectDuration() {
        return EFFECT_DURATION;
    }
}
